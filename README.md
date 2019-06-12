# Game-tracker v2.1
This will be a modified version of the DnD initiative tracker that I put on Haxme. It will include tracking for additional RPGs and other games such as MTG

----------------
Changelog:

2.1: Started work on a Dice Manager module. *Nothing is implemented yet*. This will allow people to make databases with custom saved dice combinations (such as 2d6+2) that will automatically do the roll for you, as well as graph the probability distribution for that combo. Ideally, this module will also allow you to group combos together for complex actions, and allow naming each combo within the meta-combo (eg. "hit" = 1d20+4, "dmg" = 2d6+2)

2.0: Finished loot module. Fixed the split issue so that everything displays in the correct field. Changed the Popup.java class so that it only displays the fields that are necessary. Added the "about" JMenuItem description. Finished both myRolls and randomRolls for both special and generic rules. Changed default font in Popup class. Also fixed an issue in the initiative tracker where users could enter non-Integer initiative values when prompted to reroll due to a tie.

1.5: Added Popup class that serves as a custom display popup for your loot. Currently, it does not properly display things in the correct fields because of a minor issue with the split done on the input string. Also uploaded a sample loot table (note: some lines in the file are defective for some reason, so results are not 100% accurate).

1.4: The GUI class now uses your rolls by default when you press the enter key. It also checks to make sure that all 6 fields are not only integers, but that they are in the proper range. The Loot class now properly displays the correct kind and number of coins in addition to loot.

1.3b: To the initiative tracker: Added the Window JMenu, and the Text Size and Text Style options within it. Changed the default text area size, and changed the window size to pack. This allows automatic window scaling when changing text size.

1.3a: Added save & load options to the initiative tracker. DMs may now save in the middle of an encounter (at the top of initiative) and load it back up later. DMs may also add all mobs to a planned future encounter ahead of time to be loaded when the players get to it. Added scrollbar to the initiative text field for large encounters. Minor optimizations and changes were also made in the loot table package.

1.3: The special rules portion of the loot table package is nearly complete and is useable in its current state. Random loot has not been implemented. The GUI is nearly finished for both rulesets. A new class file may be added in the future for a custom pop-up with loot descriptions because JOptionPane message dialogs do not text-wrap and the current result is hideous. 

1.2: Added most of the GUI for the loot module

1.1: Added the loot table package and set up the skeleton for that module

1.0: Moved the initiative tracker to its own package and made the main class into a menu to select different modules

0.9: Original version by Freak posted on Haxme that only included the initiative tracker

# Wiki Grabber v0.5
This will pull the stats for every single 5e monster on dandwiki (https://www.dandwiki.com/wiki/5e_Monsters) and puts the information into individual .csv files for a comprehensive database.

Model .csv file is uploaded as 0000.csv The links in the table are just notes for certain entries that have those characteristics.

We do not own anything on dandwiki. dandwiki should not contain any copyrighted material, and therefore no database items should be property of Wizards of the Coast LLC or any other entity.

----------------
Changelog:

0.5: Rows 1-8 should now all work, although lines 5 and 6 *might* not work for some unknown entries in cases where they are irregularly formatted (e.g. "vulnerabilities" shortened to "vulns"). This is complicated due to the fact that entries typically link to "/wiki/5e_SRD:Damage_Resistance_and_Vulnerability" prior to these items, therefore if you merely check for "vuln" or "resist" in the regex, then the program may put the same damage type as both a resistance *and* vulnerability. As stated, this is currently not an issue, but issues may arise if some entries are irregularly formatted (which is unconfirmed).

0.4: Issues with row 3 and 1 should be solved. Edited some of the other rows so they should be more robust. Rows 1-4 should work.

0.3: Row 4 should now work for all entries.  
For entries such as Adam, row 3 is known NOT to work.  
For entries such as Acro-Bandit, row 1 is known NOT to work.  
For entries such as Sea Serpent, all rows are working.  

0.2: Row 3 should now work for all entries. *(WRONG)

0.1: Currently the program can properly fill out the first 2 rows of the database design. Working on row 3.
