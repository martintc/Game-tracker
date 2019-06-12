##!/usr/bin/perl -w
use strict;
use LWP::UserAgent;
my($ua, $response, $mainContents, $thisLink, $thisContent, $tempString);

$ua = LWP::UserAgent->new(
	protocols_allowed 	=> ['http', 'https'],
	timeout 		=> 10,
	agent 			=> "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0", #Necessary otherwise 403 forbidden.
);
$response = $ua->get('https://www.dandwiki.com/wiki/5e_Monsters');
if ($response->is_success) {
	$mainContents = $response->decoded_content;
}else{
	die $response->status_line;
}

while($mainContents =~ /\<a href=\"(.*?)\"/g){
	$thisLink = $1;
	if($thisLink =~ /\(5e_Creature\)/){
		$response = $ua->get('https://www.dandwiki.com'.$thisLink);
		if($response->is_success){
			$thisContent = $response->decoded_content;
			if($thisLink =~ /wiki\/(.*?)_\(5e_Creature\)/){
				open(DATA, ">$1.csv");
				#-----------------------------------------------------------------------------------------------------------------------
				if($thisContent =~/\<p\>\<i\>(.*?)\<\/i\>/){										#Size/Alignment
					$tempString = $1;
					$tempString =~s/\<(.*?)\>//g;
					print DATA "\"".$tempString."\",";
				}else{
					print DATA ",";
				}						
				if($thisContent =~/Armor Class\<\/b\>(.*?)(\d+)(\s?|\S?)/){print DATA "\"".$2."\",";}else{print DATA ",";}		#AC
				if($thisContent =~/Hit Points\<\/a\>\<\/b\>(.*?)(\d+)(\s?|\S?)/){print DATA "\"".$2."\",";}else{print DATA ",";}	#HP
				if($thisContent =~/Speed\<\/a\>\<\/b\> (.*?)(\n|\<)/){print DATA "\"".$1."\",\n";}else{print DATA ",\n";}		#speed
				#-----------------------------------------------------------------------------------------------------------------------
				while($thisContent =~/\n\<td\> (.*?) \((.*?)\)/g){print DATA "\"".$1."\",";}						#ability scores
				#-----------------------------------------------------------------------------------------------------------------------
				if($thisContent =~/Strength(.*?)\<\/a\> \+( \d+|\d+)/){print DATA "\n\"".$2."\",";}					#Str save	
				elsif($thisContent =~/Saving Throws\<\/b\>(.*?)(Str|Strength)(\s*?)\+(\s*?)(\d+)/){print DATA "\n\"".$5."\",";}
				else{print DATA "\n,";}
				if($thisContent =~/Dexterity(.*?)\<\/a\> \+( \d+|\d+)/){print DATA "\"".$2."\",";}					#Dex save
				elsif($thisContent =~/Saving Throws\<\/b\>(.*?)(Dex|Dexterity)(\s*?)\+(\s*?)(\d+)/){print DATA "\"".$5."\",";}
				else{print DATA ",";}
				if($thisContent =~/Constitution(.*?)\<\/a\> \+( \d+|\d+)/){print DATA "\"".$2."\",";}					#Con save
				elsif($thisContent =~/Saving Throws\<\/b\>(.*?)(Con|Constitution)(\s*?)\+(\s*?)(\d+)/){print DATA "\"".$5."\",";}
				else{print DATA ",";}
				if($thisContent =~/Intelligence(.*?)\<\/a\> \+( \d+|\d+)/){print DATA "\"".$2."\",";}					#Int save
				elsif($thisContent =~/Saving Throws\<\/b\>(.*?)(Int|Intelligence)(\s*?)\+(\s*?)(\d+)/){print DATA "\"".$5."\",";}
				else{print DATA ",";}
				if($thisContent =~/Wisdom(.*?)\<\/a\> \+( \d+|\d+)/){print DATA "\"".$2."\",";}						#Wis save
				elsif($thisContent =~/Saving Throws\<\/b\>(.*?)(Wis|Wisdom)(\s*?)\+(\s*?)(\d+)/){print DATA "\"".$5."\",";}
				else{print DATA ",";}
				if($thisContent =~/Charisma(.*?)\<\/a\> \+( \d+|\d+)/){print DATA "\"".$2."\",\n";}					#Cha save
				elsif($thisContent =~/Saving Throws\<\/b\>(.*?)(Cha|Charisma)(\s*?)\+(\s*?)(\d+)/){print DATA "\"".$5."\",";}
				else{print DATA ",\n";}
				#-----------------------------------------------------------------------------------------------------------------------
				if($thisContent =~/\>(\s*?)Skills(.*?)((\s*?)|(.?))\<b\>/){								#Skills
					$tempString = $2;
					$tempString =~s/\<(.*?)\>//g;
					$tempString =~s/\<(.*)//g;
					$tempString =~s/(.*)\>//g;
					print DATA "\"".$tempString."\",\n";
				}else{
					print DATA ",\n";
				}
				#-----------------------------------------------------------------------------------------------------------------------
				
				
				
				
				
				#-----------------------------------------------------------------------------------------------------------------------
				close(DATA);
				last; #comment out to grab many at once.
			}
		}else{
			die $response->status_line;
		}
	}
}
