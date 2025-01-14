package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		String key;
		
		TodoUtil.loadList(l, "todolist.txt");
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;
				
			case "ls_cate":
				l.sortByCategoty();
				break;

			case "ls_name_asc":
				l.sortByName();
				isList = true;
				System.out.println("제목순으로 정렬되었습니다.");
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				System.out.println("제목역순으로 정렬되었습니다.");
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.println("날짜순으로 정렬되었습니다.");
				isList = true;
				break;
				
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();
				System.out.println("날짜역순으로 정렬되었습니다.");
				isList = true;
				break;
			
			case "find":
				key = sc.next();
				TodoUtil.find(l, key);
				break;
			
			case "find_cate":
				key = sc.next();
				TodoUtil.findCate(l, key);
				break;

			case "exit":
				quit = true;
				break;
			
			case "help":
				Menu.displaymenu();
				break;

			default:
				System.out.println("정확한 명령어를 입력해주세요. (명령어 다시 보기 - help)");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt");
	}
}
