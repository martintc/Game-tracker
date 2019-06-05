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
				if($thisContent =~/\<p\>\<i\>(.*?)\<\/i\>/){print DATA "\"".$1."\",";}else{print DATA ",";}					#Size/Alignment
				if($thisContent =~/Armor Class\<\/b\> (.*?)\(/){print DATA "\"".$1."\",";}else{print DATA ",";}					#AC
				if($thisContent =~/Hit Points\<\/a\>\<\/b\> (.*?)\(/){print DATA "\"".$1."\",";}else{print DATA ",";}				#HP
				if($thisContent =~/Speed\<\/a\>\<\/b\> (.*?)\n/){print DATA "\"".$1."\",\n";}else{print DATA ",\n";}				#speed
				#-----------------------------------------------------------------------------------------------------------------------
				while($thisContent =~/\n\<td\> (.*?) \((.*?)\)/g){print DATA "\"".$1."\",";}							#ability scores
				#-----------------------------------------------------------------------------------------------------------------------
				if($thisContent =~/Strength(.*?)\<\/a\> \+( \d*|\d*)/){print DATA "\n\"".$2."\",";}else{print DATA "\n,";}			#Str save	#They don't work for Adam...
				if($thisContent =~/Dexterity(.*?)\<\/a\> \+( \d*|\d*)/){print DATA "\"".$2."\",";}else{print DATA ",";}				#Dex save	#Either nest the whole thing in a (|)
				if($thisContent =~/Constitution(.*?)\<\/a\> \+( \d*|\d*)/){print DATA "\"".$2."\",";}else{print DATA ",";}			#Con save	#or elsif it.
				if($thisContent =~/Intelligence(.*?)\<\/a\> \+( \d*|\d*)/){print DATA "\"".$2."\",";}else{print DATA ",";}			#Int save
				if($thisContent =~/Wisdom(.*?)\<\/a\> \+( \d*|\d*)/){print DATA "\"".$2."\",";}else{print DATA ",";}				#Wis save
				if($thisContent =~/Charisma(.*?)\<\/a\> \+( \d*|\d*)/){print DATA "\"".$2."\",\n";}else{print DATA ",\n";}			#Cha save
				#-----------------------------------------------------------------------------------------------------------------------
				if($thisContent =~/\>(\s*?)Skills(.*?)((\s*?)|(.?))\<b\>/){
					$tempString = $2;
					$tempString =~s/\<(.*?)\>//g;
					$tempString =~s/\<(.*)//g;
					$tempString =~s/(.*)\>//g;
					print DATA "\"".$tempString."\",\n";
				}else{
					print DATA ",\n";
				}


				#-----------------------------------------------------------------------------------------------------------------------
				close(DATA);
				last; #comment out to grab many at once.
			}
		}else{
			die $response->status_line;
		}
	}
}
