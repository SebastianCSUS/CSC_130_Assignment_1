/** Assignment 1 - Proper Stack and Queue - 9/28/2021
 *  Sebastian Jones - Sacramento State University - CSC 130 - Professor Cooke
 */
public class Main {
    public static void main(String[] args){
        //Test.testLinkedList();
        Test.testStack();
    }
}

/** Node Class
 *      Stores an indiscriminate variable and the next node in the chain/list/array.
 */
class Node {
    Object value;
    Node next;

    //Constructors
    public Node(Object value) {
        this.value = value;
    }
    public Node(Object value, Node next) {
        this.value = value;
        this.next = next;
    }

    public Object getValue() {
        return this.value;
    }
    public Node getNext() {
        return this.next;
    }
}

/** Doubly linked list
 *
 */
class LinkedList {
    Node head;
    Node tail;

    //Constructors
    public LinkedList(){
    }
    public LinkedList(Object item) {
        this.head = new Node(item);
        this.tail = new Node(item);
    }
    public LinkedList(Object head, Object tail){
        this.head = new Node(head);
        this.tail = new Node(tail);
    }

    String about() {
        return "Author: Sebastian Jones";
    }
    void addHead(Object item) {
        if (peekHead() == null) {       //List is empty
            head = new Node(item);
            tail = new Node(item);
        } else {
            head = new Node(item, head);
        }
    }
    void addTail(Object item) {
        if (peekHead() == null) {       //List is empty
            head = new Node(item);
            tail = new Node(item);
        } else {
            tail.next = new Node(item);
            tail = tail.getNext();
        }
    }
    Object removeHead() {
        Node oldHead = head;

        head = head.getNext();

        return oldHead;
    }
    Object removeTail() {
        Node index = head;

        while(index.getNext().getNext() != null) {  //Can't go backwards so we gotta be two steps ahead
            index = index.getNext();
        }
        index = index.getNext();
        tail = index;

        Object oldTail = tail.getNext();              //Delete reference to old Tail to remove from Heap
        tail.next = null;

        return oldTail;
    }
    Object peekHead() {
        if(head == null) {
            return null;
        }
        return head.getValue();
    }
}

/** Stack class building off LinkedList
 *      All nodes are added on top of the head (new node become the new head)
 */
class Stack {
    LinkedList stack;

    //Constructors
    public Stack(){
        this.stack = new LinkedList();
    }
    public Stack(Double item){
        this.stack = new LinkedList(item);
    }

    String about() {
        return "Author: Sebastian Jones";
    }

    void push(double item) {
        stack.addHead(item);
    }

    double pop() {
        return (double)((Node)stack.removeHead()).getValue();
    }

    double peek() {
        return (double)stack.peekHead();
    }

    boolean isEmpty() {
        return stack.head == null;
    }
}
class Test {
    static void testLinkedList() {
        System.out.print("TESTING LINKED LIST\r\n");
        System.out.print("Expected List: 5, 10, Bean, 0\r\nActual List: ");

        LinkedList testList = new LinkedList();
        testList.addHead(10);
        testList.addTail("Beam");
        testList.addHead(5);
        testList.removeTail();
        testList.addTail("Bean");
        testList.addTail(0);
        testList.addHead("killme");
        testList.removeHead();

        Node index = testList.head;
        while(index != testList.tail){
            System.out.print(index.getValue() + ", ");
            index = index.getNext();
        }
        System.out.print(index.getValue()+"\r\n");
        System.out.print(testList.about()+"\r\n");
    }

    static void testStack() {
        System.out.print("TESTING STACK\r\n");
        System.out.print("Expected Stack: \r\nActual Stack: ");

        Stack testStack = new Stack();

        testStack.push(0);
        testStack.push(10);
        testStack.push(8);
        testStack.push(99);
        testStack.pop();
        System.out.print(testStack.peek());
    }
}