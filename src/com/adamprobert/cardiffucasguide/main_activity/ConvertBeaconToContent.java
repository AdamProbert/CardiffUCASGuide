package com.adamprobert.cardiffucasguide.main_activity;

import com.estimote.sdk.Beacon;

public class ConvertBeaconToContent {

	Beacon beacon;
	int beaconID;
	
	public ConvertBeaconToContent(Beacon b){
		this.beacon = b;
		this.beaconID = b.getMinor();
		
	}
	
	public ConvertBeaconToContent(long beaconID){
		this.beaconID = (int)beaconID;
	}
	
	public Content convert(){
		
		switch(beaconID){
		case 1:
			Content c1= new Content("Windows Labs","These labs have a total of 79 Windows 7 64-bit workstations."
					+ " The PCs have Intel Core i5 2.90GHz dual core processors with 4 GBytes RAM.\n\nMy 2 cents\nThis lab, like all"
					+ " in ComSci are open 24 hours a day, 365 days a year. Entry is granted through the use of RFID"
					+ " student cards issued to all ComSci students. As a first year student you will be spending most "
					+ "of your hours in this lab, completing coursework, labs and making the most of the free printing!"
					+ " \n\nLabratory Software\nThe PCs run Microsoft Windows 7 and its applications, including Microsoft "
					+ "Office (Word, PowerPoint etc), Microsoft Project, Internet Explorer, Turbo C/C++, Enthought Python "
					+ "programming language, Wolfram Mathematica for Windows, Oracle SQLplus and Oracle SQL Developer clients "
					+ "for Windows, Oracle Java 7 Development Kit for Windows, and ISEE Systems iThink business process modelling"
					+ " software.\n\nExperience and Skills\nMicrosoft Windows dominates the PC market. By using the Windows"
					+ " Applications Laboratories, students gain transferable skills which can be applied in the worlds of "
					+ "business, commerce and industry, as well as in science and research"
					,"drawable/image1","For all your Windows needs!", beaconID);
			return c1;
			
		case 2:
			Content c2 = new Content("Linux Lab","The Open Source Software Laboratory has thirty-seven PCs each with at least "
					+ "Intel Duo 3.16 GHz dual processors and 4 GBytes RAM. They have medium-range, programmable nVidia GeForce"
					+ " graphics cards usually GT 9500s or GT 630s. They run Linux Mint 13 Maya.This Lab also has space for"
					+ " students to plug in their own personal laptops to the power supply and use the csLAPNETEthernet network."
					+ " (Students also may use the wireless networks, eduroam, anywhere on the Campus).\n\nMy 2 cents\nThis lab"
					+ " is where you will spend a lot of your second year, delving into SQL and Advanced Programming!"
					+ " This lab is also fantastic for revising, always air conditioned, comfy seats and big desks.\n\nLabratory "
					+ "Software\nThe PCs run LinuxMint 13 Maya, a version of the Linux operating system. LinuxMint 13 Maya is"
					+ " a stable (Long Term Support) version of the Linux operating system that includes hundreds of open source"
					+ " applications including the standard Linux/UNIX software tools, LibreOffice — a set of free Office IT "
					+ "applications (text writer, presentation developer, spreadsheet etc), Firefox web browser, Evolution and"
					+ " Thunderbird email clients, eclipse program development environment, gimp graphics editor, and VMD 3-D "
					+ "graphics visualization program. It has open source programming languages including C, C++, perl, shell "
					+ "programming, smalltalk, prolog, Fortran 95, and python. NVidia\'s CUDA programming platform is installed"
					+ " to facilitate parallel programming using the processing power of the nVidia graphics cards. In addition "
					+ "some non-open source applications are installed including Oracle\'s Java development kit (a version of Java"
					+ " with an open source licence is also available), Oracle database applications SQLPlus Worksheet and SQLPLus"
					+ " Developer, Wolfram Mathematica symbolic mathematics software, Mathworks MATLAB interactive development"
					+ " environment, and Visual Paradigm for UML.\n\nExperience and Skills\nOpen source software is provided"
					+ " with human-readable source code which can be freely modified, improved and redistributed according to"
					+ " a non-restrictive open source software licence. The software can be used to produce new, derived"
					+ ", software which is must also be freely distributable. The open source software model results in the"
					+ " growth of communities of users and programmers who contribute to the development of the software. In"
					+ " addition, some open source software attracts commercial support provision — for example, companies"
					+ " offer support for enterprise versions of Linux. Open source software and operating systems are widely"
					+ " used in scientific research, educational institutions, and on servers supporting the Internet. Linux "
					+ "is installed on many types of computer, from embedded systems to supercomputers. With sophisticated IT"
					+ " applications like LibreOffice, open source software has been estimated to save users around $60 billion"
					+ " per annum. Such savings mean that some organisations and government bodies around the world have"
					+ " switched to open source software. In using this Laboratory, students gain valuable experience of "
					+ "open source software, and become better positioned to provide it and support it. They will also get "
					+ "experience of using commercial software in an open source environment.", "drawable/image2","For all your Linux needs!", beaconID);
			return c2;
		case 3:
			Content c3 = new Content("Mac Lab","This lab has 21½inch iMacs with 2.5 GHz Quad-Core Intel processors and 4GB SDRAM. "
					+ "The Lab is available to our undergraduate and taught post-graduate students 24 hours a day, 365 days year. "
					+ "The School has a MacOSX server which provides network services: file storage, and login account"
					+ " authentication.\n\nMy 2 cents\nI haven’t personally used this lab, due to owning a Macbook myself. It’s"
					+ " primarily used for graphic design and multimedia modules.\n\nLabratory Software\nThe iMacs run MacOSX"
					+ " 10.9 Mavericks, the current version of the Macintosh operating system. In addition to the standard MacOS"
					+ " applications, the Macs have the MATLAB Integrated Development Environment from the Mathworks, which is "
					+ "used for graphics programming and multimedia application development. They also have Wolfram Mathematica"
					+ " for symbolic mathematics and Microsoft Office for document preparation.\n\nExperience and Skills\nUsers"
					+ " will gain experience of using the Macintosh iMac platform and of commercial multimedia and graphical"
					+ " programming software. Macs have a small market share when compared with Windows but, for many years,"
					+ " Macs were regarded as a better platform for graphics. So Macs have a larger than expected presence "
					+ "in desktop publishing, advertising, animation etc. Apple also markets the popular consumer products such"
					+ " as the iPhone and iPad. The iMac is a particularly good development platform for iPhone and iPad apps."
					+ " Apple uses a different approach in marketing its computers. Unlike Microsoft, whose Windows operating "
					+ "systems run on commodity PCs, both the software and hardware on the Macintosh platform are proprietary."
					+ " Students will be able to compare their exposure to this platform with their experiences in using Microsoft"
					+ " Windows platforms and open source Linux platforms.", "drawable/image3","For all your Mac needs!", beaconID);
			return c3;
		case 4:
			Content c4 = new Content("Library","The Trevithick Library is one of many Cardiff University Libraries; this one provides"
					+ " information services for the School of Computer Science and Informatics, Physics and Astronomy and the School"
					+ " of Engineering. The library offers quiet study spaces, a Windows computer lab, thousands of journals and papers"
					+ ", Scanning/printing services and group study rooms.\n\nMy 2 cents\nThe library is a fantastic place to come and"
					+ " study if you work best in quiet, unobtrusive environments. Friendly staff, and open most hours of the week. "
					+ "Several of my friends enjoy revising here, I prefer a more lively atmosphere so retreat to the Linux Lab. The"
					+ " group study rooms are particularly useful for group project meetings and practicing presentations as all come"
					+ " equipped with a 32inch TV and peripherals for connecting laptops.\n\nOpening Times\nMonday to Friday: 08:45 "
					+ "– 21:30\nSaturday: 10:00 – 17:30\nSunday: 12:00 – 17:00\n\nFinding Information\nBooks are arranged on the "
					+ "shelves according to subject. Each book is labelled with its subject classification number and is filed on "
					+ "the shelves in this order.\nUse the Voyager library catalogue to:\n   - Locate items in any of the Cardiff "
					+ "University Libraries plus over 20 other NHS Trust / Postgraduate Centre Libraries across Wales\n   - Check"
					+ " whether items are on loan\n   - Place requests for items which are on loan\n   - Renew the period of loan for the"
					+ " items you have borrowed\n   - View a list of the items you currently have on loan or have requested You can"
					+ " access Voyager from the library computers or from any computer/mobile device with Internet access at:"
					+ " http://library.cardiff.ac.uk", "drawable/library","such study, much Information,  so library", beaconID);
			return c4;
		case 5:
			Content c5 = new Content("C/2.07","This will be one of your main lecture theatres for"
					+ " most modules from Algorithms and Data Structures to Computational Thinking.\n\nThank you for your help"
					+ " today! The data I have acquired will form the groundwork for the rest of my dissertation where I will"
					+ " be investigating the application of beacon technology in the entertainment industry. Primarily looking"
					+ " at festivals and theme parks. One last request - would you mind filling out the survey found in your "
					+ "notifications? It should only take a moment, and will provide me with great comparative primary research. Thank you!"
					,"drawable/image5", "Would you mind filling out this survey?", beaconID);
			return c5;
		case 6:
			Content c6 = new Content("Forensics and Cybersecurity","The Forensics and Cybersecurity Laboratory has 40 PCs "
					+ "with 3.4 GHz Intel Core i7-3770 processors and 8GB RAM. The Lab is available for scheduled "
					+ "classes only. It has a secure, closed, private network, which is isolated from the main University "
					+ "network. The network is divided into two separate routers, which we can configure in various ways to"
					+ " demonstrate network topology.\n\nMy 2 cents\nThis lab is really cool. Depending on which modules you"
					+ " choose you may find yourself in here; experimenting with viruses and malware or performing forensic "
					+ "analysis on HDD’s using professional forensic tools. I’d highly recommend the Security and Forensics "
					+ "module in your final year!\n\nLabratory Software\nThe PCs are installed with VMWare virtualisation "
					+ "software which allows them to host multiple operating systems such as Microsoft Windows XP, Linux an"
					+ "d DOS. They also have forensic bridges to allow the safe attachment of external devices. Compromised"
					+ " operating system images can be installed, or suspect devices can be attached for forensic analysis"
					+ ". Students use standard open source forensic tools available with Helix and Sleuthkit. The Lab also "
					+ "has licences for commercial law-enforcement tools including EnCase, XRY and FTK. For network analysis"
					+ ", open source network tools including ping, traceroute, and tcpdump are installed.\n\nExperience and "
					+ "Skills\nThe Lab gives students experience of compromised systems, of viruses and other malware. They"
					+ " learn how to analyse systems using open source and commercial forensic tools and how to help protect "
					+ "networks from malicious attacks.", "drawable/image4","Experiment with viruses and malware!", beaconID);
			return c6;
		case 7:
			Content c7 = new Content("Trevithick Seminar Room","Hello and welcome to Cardiff University!  Thank you for installing"
					+ " my app, and partaking in this field study today. This message has appeared because you are in range of"
					+ " the first Beacon! Throughout the day, you will come across multiple other Beacons that will provide you"
					+ " with information about your surrounding environment. For your phone to receive this data, I must ask you"
					+ " keep your Bluetooth on and this app open in the background. This will allow the phone to periodically scan"
					+ " for nearby Beacons. I will be around throughout the day and happy to answer any questions you might have."
					+ "\n\nThank you very much and have a fantastic day!\n\nAdam Probert "
					, "drawable/image6", "For all your sandwich making needs" , beaconID);
			return c7;
		case 8:
			Content c8 = new Content("Unused Beacon",  "Unused Beacon", "drawable/antenna2", "Unused Beacon", beaconID);
			return c8;
		case 9:
			Content c9 = new Content("Unused Beacon",  "Unused Beacon", "drawable/antenna2", "Unused Beacon", beaconID);
			return c9;
		case 99:
			Content c99 = new Content("No Beacons found yet", "Sorry, you haven't come across any beacons yet. They are located in the Seminar room, Library and Labs. If this problem persists, even when you know theres a beacon try restarting the app. Thanks!", "drawable/cardiff4", "Nothing found yet sorry, keep looking!", 99);
			return c99;
		default:
			return null;
			
		}
	}
}
