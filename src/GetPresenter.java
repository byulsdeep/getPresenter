import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GetPresenter {
	public static void main(String[] args) {
		new Roulettle();
		
	}
}
class Roulettle {
	List<String> def;
	List<String> volunteers;
	Scanner sc;

	Roulettle() {
		this.def = new ArrayList<>(
				Arrays.asList(
					// names here 
				)
		);
		this.sc = new Scanner(System.in);
		this.volunteers = new ArrayList<>();
		boolean isRun = true;
		this.setVolunteers();
		System.out.println("희망자 : " + this.volunteers);
		if (this.volunteers.size() == 4)
			isRun = false;
		if (this.volunteers.size() > 4 && isRun) {
			System.out.println(this.runRoulette(true));
			isRun = false;
		}
		if (this.volunteers.size() < 4 && isRun) {
			this.setExclude();
			System.out.println(this.addLists(this.volunteers, this.runRoulette(false)));
		}
		this.sc.close();
		// ifVolunteers == 4 break;
		// ifVolunteers > 4 run roulette with volunteers
		// ifVolunteers < 4 check for exclude, set volunteers as presenters, remove
		// volunteers from def and run roulette with def
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
			list = this.def;
		}
		Collections.shuffle(list);
		return list.subList(0, enoughVolunteers ? 4 : 4 - this.volunteers.size());
	}
	void setExclude() {
		for (int i = 0; i < this.def.size(); i++) {
			for (int j = 0; j < this.volunteers.size(); j++) {
				if (this.def.get(i).contains(this.volunteers.get(j))) {
					this.def.remove(i);
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
