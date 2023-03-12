import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GetPresenter {
	public static void main(String[] args) {
		new Roulettle().run();
	}
}
class Roulettle {
	List<String> presenters;
	List<String> volunteers;
	Scanner sc;

	Roulettle() {
		this.presenters = new LinkedList<>(Arrays.asList("john", "david", "bob", "sarah", "mary", "kate", "steven", "jenny"
		// names here
		));
		this.sc = new Scanner(System.in);
		this.volunteers = new LinkedList<>();
		// ifVolunteers == 4 break;
		// ifVolunteers > 4 run roulette with volunteers
		// ifVolunteers < 4 check for exclude, set volunteers as presenters, remove
		// volunteers from presenters and run roulette with presenters
	}
	void run() {
		boolean isRun = true;
		this.setVolunteers();
		System.out.println(this.volunteers.size() > 1 ? "희망자 : " + this.volunteers : "희망자 x");
		if (this.volunteers.size() == 4)
			isRun = false;
		if (isRun)
			this.updatePresenters();
		if (this.volunteers.size() > 4 && isRun) {
			this.printResult(this.runRoulette(true));
			isRun = false;
		}
		if (this.volunteers.size() < 4 && isRun) {
			this.setExclude();
			this.printResult(this.addLists(this.volunteers, this.runRoulette(false)));
		}
		this.sc.close();
	}
	void updatePresenters() {
		System.out.println("발표 제외자 입력 (1. 종료)");
		String s;
		while (true) {
			s = this.sc.nextLine().trim();
			if (s.equals("1")) {
				break;
			}
			for (int i = 0; i < this.presenters.size(); i++) {
				if (this.presenters.get(i).contains(s)) {
					this.presenters.remove(i);
				}
			}
		}
	}
	void printResult(List<String> list) {
		System.out.println("발표자 보기 enter");
		for (String s : list) {
			sc.nextLine();
			System.out.println(s);
		}
	}
	void setVolunteers() {
		System.out.println("발표 희망자 입력 (1. 종료) ");
		String s;
		boolean contains;
		while (true) {
			s = this.sc.nextLine().trim();
			if (s.equals("1")) {
				break;
			}
			if (this.volunteers.size() > 0) {
				contains = false;
				for (String stud : this.volunteers) {
					if (stud.contains(s)) {
						System.out.println("이미 있는 희망자입니다.");
						contains = true;
						break;
					}
				}
				if (!contains) {
					this.volunteers.add(s);
				}
			} else {
				this.volunteers.add(s);
			}
		}
	}
	List<String> runRoulette(boolean enoughVolunteers) {
		List<String> list = null;
		if (enoughVolunteers) {
			list = this.volunteers;
		} else {
			list = this.presenters;
		}
		Collections.shuffle(list);
		return list.subList(0, enoughVolunteers ? 4 : list.size() > 3 ? 4 - this.volunteers.size() : list.size());
	}
	void setExclude() {
		for (int i = 0; i < this.presenters.size(); i++) {
			for (int j = 0; j < this.volunteers.size(); j++) {
				if (this.presenters.get(i).contains(this.volunteers.get(j))) {
					this.presenters.remove(i);
				}
			}
		}
	}
	List<String> addLists(List<String> a, List<String> b) {
		List<String> added = new ArrayList<>();
		added.addAll(a);
		added.addAll(b);
		return added;
	}
}
