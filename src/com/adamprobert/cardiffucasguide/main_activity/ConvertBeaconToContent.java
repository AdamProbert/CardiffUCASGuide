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
			Content c1= new Content("Windows Lab","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "drawable/image1","For all your Windows needs!", 1);
			return c1;
			
		case 2:
			Content c2 = new Content("Linux Lab","Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of de Finibus Bonorum et Malorum (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, Lorem ipsum dolor sit amet.., comes from a line in section 1.10.32.The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from de Finibus Bonorum et Malorum by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham", "drawable/image2","For all your Linux needs!", 2);
			return c2;
		case 3:
			Content c3 = new Content("Mac Lab","There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which dont look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isnt anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.", "drawable/image3","For all your Mac needs!", 3);
			return c3;
		case 4:
			Content c4 = new Content("Library","There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which dont look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isnt anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.", "drawable/image3","For all your Mac needs!", 3);
			return c4;
		case 5:
			Content c5 = new Content("C/2.07","Would you mind filling out this survey? Thankyou!","drawable/antenna2", "SURVEY TIME", beaconID);
			return c5;
		case 6:
			Content c6 = new Content("Security & Forensics","There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which dont look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isnt anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.", "drawable/image3","For all your Mac needs!", 3);
			return c6;
		case 7:
			Content c7 = new Content("Upstairs Kitchen","So, you're in the upstairs kitchen.. Enjoy the cleaner kitchen huh? Making your sandwiches, no george forman anymore though. Unlucky.", "drawable/antenna2", "For all your sandwich making needs" , beaconID);
			return c7;
		case 8:
			Content c8 = new Content("Hallway",  "On your way somwhere? Thought you'd just pass through. I don't think so. GET BACK TO YOUR ROOM!", "drawable/antenna2", "Just passing through", beaconID);
			return c8;
		case 9:
			Content c9 = new Content("Downstairs Kitchen", "I know what you're thinking.. What an absolute shithole, this place should be burned from the ground up. Leave Scott in here too. Damn messy fucker.", "drawable/antenna2", "What a terrible looking kitchen", beaconID);
			return c9;
		case 99:
			Content c99 = new Content("No Beacons found yet", "Nothing found yet sorry, keep looking!", "drawable/antenna2", "Nothing found yet sorry, keep looking!", 99);
			return c99;
		default:
			return null;
			
		}
	}
}
