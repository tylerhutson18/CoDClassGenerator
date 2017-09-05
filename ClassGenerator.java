import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/*
 * 		Call of Duty: Black Ops 2 Class Randomizer
 * 		Code by Tyler Hutson
 * 		Information retrieved from ign.com
 * 
 * 
 * 		Goal: make killstreaks print in order / use hashmap to map point values and do logic compute?
 * 	
 * 		Have: ARs, SMGs, Shotguns
 * 		Need: LMGs, Snipers, ...Assault Shield?
 * 
 */

public class ClassGenerator {

	public static int classCount = 0;		/* number of elements for randomized class */
	
	public static void main(String[] args) {

		
		generateClass();
		
		validateClass();
		
		
		// altering
		/*
		rerollAll();
		rerollOne("Primary");
		rerollOne("Secondary");
		rerollOne("Perk 1");
		rerollOne("Perk 2");
		rerollOne("Perk 3");
		rerollOne("Lethal");
		rerollOne("Tactical");
		*/
	}
	
	public static void generateClass() {
		int primaryGen = ThreadLocalRandom.current().nextInt(0, 3);
	
		if (primaryGen == 0) {
			smgGen();
			attachmentGen();
		} else if (primaryGen == 1) {
			arGen();
			arAttachments();
		} else if (primaryGen == 2) {
			shotgunGen();
			shAttachments();
		}
		
		secondaryGen();
		perk1Gen();
		perk2Gen();
		perk3Gen();
		lethalGen();
		tacticalGen();
		killstreakGen();
		wildcardGen();
	}

	public static void validateClass() {
		System.out.println("\nValidating class...");

		// check if number of items in class are > 10, handling an error
		if (classCount > 10) {
			System.out.println("Illegal class. You have " + classCount + " out of 10 class items. Remove " + (classCount - 10) + " items from the class (you pick).");
		} else {
			System.out.println("Valid. " + classCount + "/10 class slots used.");
		}
	}

/*  Assault Rifle  */
	
	public static void arGen() {

		ArrayList<String> ars = new ArrayList<String>(8);
    		/*0*/ars.add("MTAR");     /*1*/ars.add("TYPE 25"); /*2*/ars.add("SWAT-556");
		/*3*/ars.add("FAL-OSW"); /*4*/ars.add("M27"); /*5*/ars.add("SCAR-H");	
		/*6*/ars.add("SMR");    /*7*/ars.add("M8A1"); /*8*/ars.add("AN-94");

		int randAR = ThreadLocalRandom.current().nextInt(0, ars.size());

		System.out.println("Primary weapon: " + ars.get(randAR));

		// remove so wildcard doesn't pick it again
		ars.remove(randAR);

		classCount++;
	}

/*
 * Attachments		
 */

	public static void arAttachments() {

		ArrayList<String> att = new ArrayList<String>(14);
		/*0*/att.add("Reflex"); /*1*/att.add("ACOG"); /*2*/att.add("Target Finder"); /*3*/att.add("Hybrid Optic"); /*4*/att.add("Suppressor"); /*5*/att.add("Fast Mag");	
		/*7*/att.add("Fore Grip"); /*8*/att.add("Laser Sight"); /*9*/att.add("Adjustable Stock"); /*10*/att.add("Quickdraw"); /*11*/att.add("Millimeter Scanner"); /*12*/att.add("Grenade Launcher");
		/*13*/att.add("Select Fire"); /*14*/att.add("FMJ"); /*15*/att.add("Extended Clip");

		Boolean loopVar = true;

		// loop to make sure no incompatible attachments
		while(loopVar == true) {

			int randAtt1 = ThreadLocalRandom.current().nextInt(0, att.size());
			int randAtt2 = ThreadLocalRandom.current().nextInt(0, att.size());

			// check for incompatible attachments
			if(randAtt1 == randAtt2  || randAtt2 == randAtt1 
					|| randAtt1 == 0 && randAtt2 == 1   || randAtt2 == 0 && randAtt1 == 1			// reflex/acog
					|| randAtt1 == 0 && randAtt2 == 2   || randAtt2 == 0 && randAtt1 == 2			// reflex/tf
					|| randAtt1 == 0 && randAtt2 == 3   || randAtt2 == 0 && randAtt1 == 3 			// reflex/hybrid optic
					|| randAtt1 == 0 && randAtt2 == 11  || randAtt2 == 0 && randAtt1 == 11			// reflex/mms
					|| randAtt1 == 1 && randAtt2 == 2   || randAtt2 == 1 && randAtt1 == 2			// acog/tf
					|| randAtt1 == 1 && randAtt2 == 3   || randAtt2 == 1 && randAtt1 == 3			// acog/hybrid optic
					|| randAtt1 == 1 && randAtt2 == 11  || randAtt2 == 1 && randAtt1 == 11			// acog/mms
					|| randAtt1 == 2 && randAtt2 == 3   || randAtt2 == 2 && randAtt1 == 3			// tf/hybrid
					|| randAtt1 == 2 && randAtt2 == 11  || randAtt2 == 2 && randAtt1 == 11			// tf/mms
					|| randAtt1 == 3 && randAtt2 == 12  || randAtt2 == 3 && randAtt1 == 12			// hybrid optic/grenade
					|| randAtt1 == 3 && randAtt2 == 11  || randAtt2 == 3 && randAtt1 == 11			// hybrid optic/mms
					|| randAtt1 == 4 && randAtt2 == 14  || randAtt2 == 4 && randAtt1 == 14			// suppressor/fmj
					|| randAtt1 == 5 && randAtt2 == 15  || randAtt2 == 5 && randAtt1 == 15			// fast mag/extended clip
					|| randAtt1 == 7 && randAtt2 == 12  || randAtt2 == 7 && randAtt1 == 12 			// fore grip/grenade
					|| randAtt1 == 11 && randAtt2 == 14 || randAtt2 == 11 && randAtt1 == 14			// mms/fmj
					|| randAtt1 == 11 && randAtt2 == 1  || randAtt2 == 11 && randAtt1 == 1
					) {
				loopVar = true;
			} else {

				System.out.println("Attachments: " + att.get(randAtt1) + ", " + att.get(randAtt2));

				loopVar = false;
				classCount++; classCount++;
			}

		}

	}
		
/*  
 * 	SMG
 */	
	
	public static void smgGen() {
		
		ArrayList<String> smgs = new ArrayList<String>(5);
		/*0*/smgs.add("MP7"); /*1*/smgs.add("PDW-57"); /*2*/smgs.add("VECTOR K10"); 
		/*3*/smgs.add("MSMC"); /*4*/smgs.add("CHICOM CQB"); /*5*/smgs.add("SKORPION EVO");		
				
		int randSMG = ThreadLocalRandom.current().nextInt(0, smgs.size());
	
		System.out.println("Primary weapon: " + smgs.get(randSMG));
		
		// remove so wildcard doesn't pick it again
		smgs.remove(randSMG);
	
		classCount++;
	}
	
	
/*
 *  Attachments	
 */
	
	public static void attachmentGen() {
		
		ArrayList<String> att = new ArrayList<String>(14);
		/*0*/att.add("Reflex"); /*1*/att.add("Laser Sight"); /*2*/att.add("Suppressor"); /*3*/att.add("Fast Mag"); /*4*/att.add("EO Tech"); /*5*/att.add("Fore Grip");	
		/*7*/att.add("Quickdraw"); /*8*/att.add("FMJ"); /*9*/att.add("Long Barrel"); /*10*/att.add("Target Finder"); /*11*/att.add("Stock"); /*12*/att.add("Extended Clip");
		/*13*/att.add("Select Fire"); /*14*/att.add("Rapid Fire"); /*15*/att.add("MMS");
				
		Boolean loopVar = true;
		
		// loop to make sure no incompatible attachments
		while(loopVar == true) {

			int randAtt1 = ThreadLocalRandom.current().nextInt(0, att.size());
			int randAtt2 = ThreadLocalRandom.current().nextInt(0, att.size());
	
			// loop
			if (randAtt1 == randAtt2 || randAtt2 == randAtt1			// if the attachments are the same
					 || randAtt1 == 0  && randAtt2 == 4					// reflex check
					 || randAtt1 == 0  && randAtt2 == 10
					 || randAtt1 == 0  && randAtt2 == 15				
					 || randAtt2 == 0  && randAtt1 == 4
					 || randAtt2 == 0  && randAtt1 == 10
					 || randAtt2 == 0  && randAtt1 == 15
					 || randAtt1 == 4  && randAtt2 == 10				// EO tech check
					 || randAtt1 == 4  && randAtt2 == 15
					 || randAtt2 == 4  && randAtt1 == 10				
					 || randAtt2 == 4  && randAtt1 == 15
					 || randAtt1 == 10 && randAtt2 == 15				// target finder check
					 || randAtt2 == 10 && randAtt1 == 15
					 || randAtt1 == 8  && randAtt2 == 15				// FMJ MMS check
					 || randAtt2 == 8  && randAtt1 == 15
					 || randAtt1 == 2  && randAtt2 == 8					// FMJ suppressor check
					 || randAtt2 == 2  && randAtt1 == 8
					 || randAtt1 == 3  && randAtt2 == 12				// fast/extended mag check
					 || randAtt2 == 3  && randAtt1 == 12
					 || randAtt1 == 2  && randAtt2 == 9					// long barrel suppressor check
					 || randAtt2 == 2  && randAtt1 == 9 ) {
				
				loopVar = true;
				
			} else {
				
				System.out.println("Attachments: " + att.get(randAtt1) + ", " + att.get(randAtt2));
				
				loopVar = false;
				
				classCount++; classCount++;
			}
		}
	}
	
/*
 * Shotgun	
 */
	
	public static void shotgunGen() {
		
		ArrayList<String> shotties = new ArrayList<String>(3);
		/*0*/shotties.add("R870 MCS"); /*1*/shotties.add("S12"); 
		/*2*/shotties.add("KSG"); /*3*/shotties.add("M1216"); 
		
		int randShotgun = ThreadLocalRandom.current().nextInt(0, shotties.size());
		
		System.out.println("Primary weapon: " + shotties.get(randShotgun));
		
		// remove so wildcard doesn't pick it again
		shotties.remove(randShotgun);
	
		classCount++;
		
	}
	
/*
 * 
 */
	
	public static void shAttachments() {
		
		ArrayList<String> att = new ArrayList<String>(14);
		/*0*/att.add("Reflex"); /*1*/att.add("Long Barrel"); /*2*/att.add("Fast Mag"); 
		/*3*/att.add("Laser Sight"); /*4*/att.add("Stock"); /*5*/att.add("Suppressor");	
		/*6*/att.add("Extended Clip"); /*7*/att.add("Quickdraw"); /*8*/att.add("MMS"); 
				
		Boolean loopVar = true;
		
		// loop to make sure no incompatible attachments
		while(loopVar == true) {

			int randAtt1 = ThreadLocalRandom.current().nextInt(0, att.size());
			int randAtt2 = ThreadLocalRandom.current().nextInt(0, att.size());
			
			if(randAtt1 == randAtt2 || randAtt2 == randAtt1
					|| randAtt1 == 1 && randAtt2 == 5 || randAtt2 == 1 && randAtt1 == 5 		// long barrel/suppressor
					|| randAtt1 == 2 && randAtt2 == 6 || randAtt2 == 2 && randAtt2 == 6			// fast mag/ extended clip
					) {
				
				loopVar = true;
			} else {
				
				System.out.println("Attachments: " + att.get(randAtt1) + ", " + att.get(randAtt2));
				
				loopVar = false;
				
				classCount++; classCount++;	
			}
		}
	}
	
/*  
 *  Secondary
 */	
	
	public static void secondaryGen() {
		
		ArrayList<String> secondaries = new ArrayList<String>(9);
		/*0*/secondaries.add("Five-Seven"); /*1*/secondaries.add("TAC-45"); /*2*/secondaries.add("B23R"); /*3*/secondaries.add("Executioner"); /*4*/secondaries.add("KAP-40");
		/*5*/secondaries.add("SMAW"); /*6*/secondaries.add("FHJ-18 AA"); /*7*/secondaries.add("RPG"); 
		/*8*/secondaries.add("Crossbow"); /*9*/secondaries.add("Ballistic Knife");
		
		int randSecondary = ThreadLocalRandom.current().nextInt(0, secondaries.size());
		
		System.out.println("Secondary: " + secondaries.get(randSecondary));
		
		classCount++;
	}
	
/*  
 * 	Perks
 */	
	public static void perk1Gen() {
		
		ArrayList<String> perk1 = new ArrayList<String>(5);
		/*0*/perk1.add("Lightweight"); /*1*/perk1.add("Flak Jacket"); /*2*/perk1.add("Blind Eye"); /*3*/perk1.add("Hardline"); /*4*/perk1.add("Ghost"); 
	
		// rng
		int randP1 = ThreadLocalRandom.current().nextInt(0, perk1.size());
		
		System.out.println("\nPerk 1: " + perk1.get(randP1));

		// remove so wildcard doesn't pick it again
		perk1.remove(randP1);
		
		classCount++;
	}
	
	public static void perk2Gen() {
		
		ArrayList<String> perk2 = new ArrayList<String>(5);
		/*0*/perk2.add("Hard Wired"); /*1*/perk2.add("Scavenger"); /*2*/perk2.add("Cold Blooded"); /*3*/perk2.add("Fast Hands"); /*4*/perk2.add("Toughness");
		
		int randP2 = ThreadLocalRandom.current().nextInt(0, perk2.size());

		System.out.println("Perk 2: " + perk2.get(randP2));

		// remove so wildcard doesn't pick it again
		perk2.remove(randP2);
		
		classCount++;

	}
	
	public static void perk3Gen() {
			
		ArrayList<String> perk3 = new ArrayList<String>(6);
		/*0*/perk3.add("Dexterity"); /*1*/perk3.add("Engineer"); /*2*/perk3.add("Dead Silence"); 
		/*3*/perk3.add("Extreme Conditioning"); /*4*/perk3.add("Tactical Mask"); /*5*/perk3.add("Awareness");	
		
		int randP3 = ThreadLocalRandom.current().nextInt(0, perk3.size());
		
		System.out.println("Perk 3: " + perk3.get(randP3));

		// remove so wildcard doesn't pick it again
		perk3.remove(randP3);
		
		classCount++;

	}

/*  
 * 	'Nades
 */	
	
	public static void lethalGen() {
		
		ArrayList<String> lethals = new ArrayList<String>(5);
		/*0*/lethals.add("Grenade"); /*1*/lethals.add("Combat Axe"); /*2*/lethals.add("Semtex"); 
		/*3*/lethals.add("C4"); /*4*/lethals.add("Bouncing Betty"); /*5*/lethals.add("Claymore"); 
		
		// rng
		int randLethal = ThreadLocalRandom.current().nextInt(0, lethals.size());
			
		System.out.println("\nLethal equipment: " + lethals.get(randLethal));
		
		classCount++;

	}
	
	
	public static void tacticalGen() {
		
		ArrayList<String> tacticals = new ArrayList<String>(7);
		/*0*/tacticals.add("Smoke Grenade"); /*1*/tacticals.add("Concussion"); /*2*/tacticals.add("EMP Grenade"); /*3*/tacticals.add("Sensor Grenade"); 
		/*4*/tacticals.add("Flashbang"); /*5*/tacticals.add("Shock Charge"); /*6*/tacticals.add("Black Hat"); /*7*/tacticals.add("Tactical Insertion");  
		
		int randTactical = ThreadLocalRandom.current().nextInt(0, tacticals.size());
		
		System.out.println("Tactical equipment: " + tacticals.get(randTactical));
		
		tacticals.remove(randTactical);

		classCount++;

	}

/*
 * Killstreaks	
 */
	
	public static void killstreakGen() {
		
		ArrayList<String> killstreak = new ArrayList<String>();
		/*0*/killstreak.add("UAV"); /*1*/killstreak.add("RC-XD"); /*2*/killstreak.add("Hunter Killer"); /*3*/killstreak.add("Care Package"); /*4*/killstreak.add("Counter-UAV"); 
		/*5*/killstreak.add("Guardian"); /*6*/killstreak.add("Hellstorm Missile"); /*7*/killstreak.add("Lightning Strike"); /*8*/killstreak.add("Sentry Gun"); /*9*/killstreak.add("Death Machine");
		/*10*/killstreak.add("War Machine"); /*11*/killstreak.add("Dragonfire"); /*12*/killstreak.add("AGR"); /*13*/killstreak.add("Stealth Chopper"); /*14*/killstreak.add("Orbital VSAT"); 
		/*15*/killstreak.add("Escort Drone"); /*16*/killstreak.add("EMP Systems"); /*17*/killstreak.add("Warthog"); /*18*/killstreak.add("Lodestar"); /*19*/killstreak.add("VTOL Warship"); 
		/*20*/killstreak.add("K9 Unit"); /*21*/killstreak.add("Swarm"); 
		
		

		Boolean loopVar = true;
		
		while (loopVar == true) {
			
			// create three random numbers to pick the three streaks
			int randStreak1 = ThreadLocalRandom.current().nextInt(0, killstreak.size());
			int randStreak2 = ThreadLocalRandom.current().nextInt(0, killstreak.size());
			int randStreak3 = ThreadLocalRandom.current().nextInt(0, killstreak.size());

			// repeat the loop if any of them pick the same number
			if (randStreak1 == randStreak2
					|| randStreak1 == randStreak3
					|| randStreak2 == randStreak3) {
				
				loopVar = true;
		
			} else {
				
				// order killstreaks
				Boolean breakout = false;
				while (breakout == false) {

					if (randStreak1 > randStreak2) {
						int temp = 0;
						temp = randStreak1;
						randStreak1 = randStreak2;
						randStreak2 = temp;
					}

					if (randStreak2 > randStreak3) {
						int temp = 0;
						temp = randStreak2;
						randStreak2 = randStreak3;
						randStreak3 = temp;
					}

					if (randStreak1 > randStreak3) {
						int temp = 0;
						temp = randStreak1;
						randStreak1 = randStreak3;
						randStreak3 = temp;
					}

					// once they're in order, break out of the loop
					if (randStreak1 < randStreak2 && randStreak2 < randStreak3 && randStreak1 < randStreak3) {
						breakout = true;
					}

				}

				// print and break out of loop
				System.out.println("\nKillstreaks: " + killstreak.get(randStreak1) + ", " + killstreak.get(randStreak2) + ", " + killstreak.get(randStreak3));
				loopVar = false;
				
			}
		}
	}
	
	

/*
 * Wildcards
 */
	
	public static void wildcardGen() {
		
		ArrayList<String> wildcards = new ArrayList<String>(7);
		/*0*/wildcards.add("Primary Gunfighter"); /*1*/wildcards.add("Secondary Gunfighter"); /*2*/wildcards.add("Overkill"); /*3*/wildcards.add("Perk 1 Greed"); 
		/*4*/wildcards.add("Perk 2 Greed"); /*5*/wildcards.add("Perk 3 Greed"); /*6*/wildcards.add("Danger Close"); /*7*/wildcards.add("Tactician"); 
		
		int randWildcard = ThreadLocalRandom.current().nextInt(0, wildcards.size());
		
		if (randWildcard == 0) {
			System.out.println("\nYour wildcard is: " + wildcards.get(randWildcard) + "\nGenerating additional primary attachment...");
			
			classCount++;
		} else if (randWildcard == 1) {
			System.out.println("\nYour wildcard is: " + wildcards.get(randWildcard) + "\nGenerating additional secondary attachment...");
			
			classCount++;
		} else if (randWildcard == 2) {
			System.out.println("\nYour wildcard is: " + wildcards.get(randWildcard) + "\nGenerating second weapon...");
			
			int r = ThreadLocalRandom.current().nextInt(0, 3);

			if (r == 0) {
				smgGen();
			} else if (r == 1) {
				arGen();
			} else if (r == 2) {
				shotgunGen();
			}
			
			classCount++;
		} else if (randWildcard == 3) {
			System.out.println("\nYour wildcard is: " + wildcards.get(randWildcard) + "\nGenerating another perk...");
			perk1Gen();
			classCount++;
		} else if (randWildcard == 4) {
			System.out.println("\nYour wildcard is: " + wildcards.get(randWildcard) + "\nGenerating another perk...");
			perk2Gen();
			classCount++;
		} else if (randWildcard == 5) {
			System.out.println("\nYour wildcard is: " + wildcards.get(randWildcard) + "\nGenerating another perk...");
			perk3Gen();
			classCount++;
		} else if (randWildcard == 6) {
			System.out.println("\nYour wildcard is: " + wildcards.get(randWildcard));
			classCount++;
		} else if (randWildcard == 7) {
			System.out.println("\nYour wildcard is: " + wildcards.get(randWildcard) + "\nGenerating another tactical...");
			tacticalGen();
			classCount++;
		}
		
	}
	
/*  
 * 	Re Roll
 */	
	
	public static void rerollOne(String perk) {
		if (perk.equals("Primary")) {
			System.out.println("\nRe-rolling primary.");
			smgGen();
		} else if (perk.equals("Perk 1")) {
			System.out.println("\nRe-rolling perk 1.");
			perk1Gen();
		} else if (perk.equals("Perk 2")) {
			System.out.println("\nRe-rolling perk 2.");
			perk2Gen();
		} else if (perk.equals("Perk 3")) {
			System.out.println("\nRe-rolling perk 3.");
			perk3Gen();
		} else if (perk.equals("Secondary")) {
			System.out.println("\nRe-rolling secondary.");
			secondaryGen();
		} else if (perk.equals("Lethal")) {
			System.out.println("\nRe-rolling lethal.");
			lethalGen();
		} else if (perk.equals("Tactical")) {
			System.out.println("\nRe-rolling tactical.");
			tacticalGen();
		} else if (perk.equals("Killstreak")) {
			System.out.println("\nRe-rolling killstreak.");
			killstreakGen();
		}
		
	}
	
	public static void rerollAll() {
		System.out.println("Re-rolling.\n");
		smgGen(); classCount--;
		secondaryGen(); classCount--;
		perk1Gen(); classCount--;
		perk2Gen(); classCount--;
		perk3Gen(); classCount--;
		lethalGen(); classCount--;
		tacticalGen(); classCount--;
		killstreakGen(); classCount--;
		
	}
}
