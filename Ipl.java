package com.jsp.ipl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ipl 
{
	List l=new ArrayList();
	Scanner sc=new Scanner(System.in);
	public void addTeam()
	{
		System.out.println("enter team name");
		String name=sc.next();
		System.out.println("enter team point");
		int points=sc.nextInt();
		char[]wrl=new char[5];
		for(int i=0;i<wrl.length;i++)
		{
			wrl[i]=sc.next().charAt(0);
		}
		Team t=new Team(name,points,wrl);
		l.add(t);
	}
	public void twoLoss(Team t)
	{
		char[]ch=t.wrl;
		for(int i=0;i<ch.length;i++)
		{
			if(i!=ch.length-1)
			{
				if(ch[i]=='l'&&ch[i+1]=='l')
				{
					System.out.print(t.name+" ");
					System.out.println(t.points);
					return;
				}
			}
		}
	}
	public void dynamicCheck(Team t,char ch,int c)
	{
		char[]ch1=t.wrl;
		int count=0;
		for(int i=0;i<ch1.length;i++)
		{
			if(ch1[i]==ch)
			{
				count++;
				if(count==c)
				{
					System.out.println(t.name);
					System.out.println(t.points);
					return;
				}
			}
			else
			{
				count=0;
			}
		}
	}

}
