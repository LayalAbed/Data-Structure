package application;

import java.util.Date;

public class SingelLinkList {
	Node head, tail;

	public SingelLinkList() {
		this.head = null;
	}

	int caunt;

	public void SingelAddLastMartyar(Martyr data) {
		Node node = new Node(data);
		node.martyr = data;
		if (caunt == 0) {
			head = node;
			tail = node;
			node.next = head;
		} else {
			tail.next = node;
			tail = node;
			tail.next = head;
		}
		caunt++;
	}
	
	public void SingelAddLastLocation(Object data) {
		Node node = new Node(data);
		node.martyr = (Martyr) data;
		if (caunt == 0) {
			head = node;
			tail = node;
			node.next = head;
		} else {
			tail.next = node;
			tail = node;
			tail.next = head;
		}
		caunt++;
	}
}

