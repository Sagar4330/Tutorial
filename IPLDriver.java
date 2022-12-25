package com.jsp.ipl;

import java.util.List;

public class IPLDriver 
{
	public static void main(String[] args)
	{
		
		 Ipl i=new  Ipl ();
		for(;;)
		{
			System.out.println("1.addteam 2.2cl 3.dc");
			{
				switch(i.sc.nextInt())
				{
				case 1:i.addTeam();
				break;
				case 2:
				{
					List l=i.l;
					for(int i1=0;i1<l.size();i1++)
					{
						Team t=(Team)l.get(i1);
						i.twoLoss(t);
						
					}
				}
				break;
				case 3:
				{
					List l=i.l;
					System.out.println("enter w r l");
					char ch=i.sc.next().charAt(0);
					System.out.println("how mant times u need");
					int count=i.sc.nextInt();
					for(int i1=0;i1<l.size();i1++)
					{
						Team t=(Team)l.get(i1);
						i.dynamicCheck(t, ch, count);
						
					}
					
				}
					
				}
			}
		}
		
	}

}
