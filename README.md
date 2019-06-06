# Game-tracker v1.3b
This will be a modified version of the DnD initiative tracker that I put on Haxme. It will include tracking for additional RPGs and other games such as MTG

----------------
Changelog:

1.3b: To the initiative tracker: Added the Window JMenu, and the Text Size option within it. Changed the default text area size, and changed the window size to pack. This allows automatic window scaling when changing text size.

1.3a: Added save & load options to the initiative tracker. DMs may now save in the middle of an encounter (at the top of initiative) and load it back up later. DMs may also add all mobs to a planned future encounter ahead of time to be loaded when the players get to it. Added scrollbar to the initiative text field for large encounters. Minor optimizations and changes were also made in the loot table package.

1.3: The special rules portion of the loot table package is nearly complete and is useable in its current state. Random loot has not been implemented. The GUI is nearly finished for both rulesets. A new class file may be added in the future for a custom pop-up with loot descriptions because JOptionPane message dialogs do not text-wrap and the current result is hideous. 

1.2: Added most of the GUI for the loot module

1.1: Added the loot table package and set up the skeleton for that module

1.0: Moved the initiative tracker to its own package and made the main class into a menu to select different modules

0.9: Original version by Freak posted on Haxme that only included the initiative tracker

# Wiki Grabber v0.3
This will pull the stats for every single 5e monster on dandwiki (https://www.dandwiki.com/wiki/5e_Monsters) and puts the information into individual .csv files for a comprehensive database.

Model .csv file is uploaded as 0000.csv The links in the table are just notes for certain entries that have those characteristics.

We do not own anything on dandwiki. dandwiki should not contain any copyrighted material, and therefore no database items should be property of Wizards of the Coast LLC or any other entity.

----------------
Changelog:

0.3: Row 4 should now work for all entries.  
For entries such as Adam, row 3 is known NOT to work.  
For entries such as Acro-Bandit, row 1 is known NOT to work.  
For entries such as Sea Serpent, all rows are working.  

0.2: Row 3 should now work for all entries. *(WRONG)

0.1: Currently the program can properly fill out the first 2 rows of the database design. Working on row 3.
