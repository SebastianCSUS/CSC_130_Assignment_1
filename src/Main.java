/** Assignment 1 - Proper Stack and Queue - 9/28/2021
 *  Sebastian Jones - Sacramento State University - CSC 130 - Professor Cooke
 */
public class Main {
    public static void main(String[] args){
        //Test.testLinkedList();
        //Test.testStack();
        //Test.testQueue();
        LinkedList test = new LinkedList();
    }
}

/** Node Class
 *      Stores an indiscriminate variable and the next node in the chain/list/array.
 */
class Node {
    Object value;
    Node next;

    //Constructors
    public Node(){
    }
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
 *      Keeps track of beginning and end of list for O(1) efficiency
 */
class LinkedList {
    Node head;
    Node tail;
    Node pool = new Node();
    Integer poolSize = 0;

    //Constructors
    public LinkedList(){
        Node poolIndex = pool;
        for(int i = 1; i < 10; i++) {
            poolIndex.next = new Node(i);
            System.out.println("Created pool node: " + i);
            poolIndex = poolIndex.next;
        }
        System.out.println("Current node is " + pool.value + ". Next node is " + pool.next.value);
    }
    public LinkedList(Object item) {
        this.head = new Node(item);
    }

    String about() {
        return "Author: Sebastian Jones";
    }
    void addHead(Object item) {
        if (peekHead() == null) {       //List is empty, make new head
            head = new Node(item);
        } else if (tail == null){
            tail = head;
            head = new Node(item, tail);
        } else {
            head = new Node(item, head);
        }
    }
    void addTail(Object item) {
        if (peekHead() == null) {       //List is empty, make new head instead of tail
            head = new Node(item);
        } else if (head.getNext() == null) {
            tail = new Node(item);
            head.next = tail;
        } else {
            Node newTail = new Node(item);
            tail.next = newTail;
            tail = newTail;
        }
    }
    Object removeHead() {
        Node oldHead = head;
        this.head = head.getNext();
        return oldHead.getValue();
    }
    Object removeTail() {
        Node index = head;
        if(index == null) {
            return null;                                    //TODO: ASK IF THIS IS CORRECT
        } else if(index.getNext() == null) {
            removeHead();
            return null;
        }

        while(index.getNext().getNext() != null) {  //Can't go backwards so we gotta be two steps ahead...
            index = index.getNext();                //...because we'll be cutting the next one (current tail) out...
        }                                           //...and setting the current index as the new tail

        tail = index;

        Object oldTail = tail.getNext();
        tail.next = null;                           //Delete reference to old Tail to remove from Heap

        return oldTail;
    }

    Object peekHead() {
        if(head == null) {
            return null;
        }
        return head.getValue();
    }

    String outputToString() {
        Node index = head;
        String output = "";
        StringBuilder sb = new StringBuilder();
        if(index == null) {
            return "Empty\r\n";
        }
        System.out.print("Head -> ");
        while(index.getNext() != null) {
            output = sb.append(index.getValue()).append(", ").toString();
            index = index.getNext();
        }
        output += (index.getValue() + " <- Tail\r\n");

        return output;
    }
}

/** Stack - Self explanatory
 *      Stack builds on head, removes off head
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
        return (double)(stack.removeHead());
    }

    double peek() {
        return (double)stack.peekHead();
    }

    void outputToString() {
        String output = stack.outputToString();
        System.out.print(output);
    }

    boolean isEmpty() {
        return stack.head == null;
    }
}

/** Queue - Self explanatory
 *      Builds on tail, removes off head
 */
class Queue {
    LinkedList queue;

    //Constructors
    public Queue() {
        this.queue = new LinkedList();
    }
    public Queue(String item) {
        this.queue = new LinkedList(item);
    }

    String about() {
        return "Author: Sebastian Jones";
    }

    void enqueue(String item) {
        queue.addTail(item);
    }

    String dequeue() {
        return (String)(queue.removeHead());
    }

    String peek() {
        return (String)(queue.peekHead());
    }

    void outputToString() {
        String output = queue.outputToString();
        System.out.print(output);
    }

    boolean isEmpty() {
        return queue.head == null;
    }
}

/** Procedures to test other classes - disregard this
 *      I could probably automate this for randomness but that's a lot of work - maybe later
 *          I think this works for now
 */
class Test {
    static void testLinkedList() {
        System.out.print("TESTING LINKED LIST\r\n");

        LinkedList testLinkedList = new LinkedList(1);
        testLinkedList.removeHead();
        System.out.print(testLinkedList.outputToString());
        System.out.println("Adding tail:");
        testLinkedList.addTail(2);
        System.out.print(testLinkedList.outputToString());
        System.out.println("Adding head:");
        testLinkedList.addHead(5);
        System.out.print(testLinkedList.outputToString());
        System.out.println("Adding head:");
        testLinkedList.addHead(7);
        System.out.print(testLinkedList.outputToString());
        System.out.println("Adding tail:");
        testLinkedList.addTail(88);
        System.out.print(testLinkedList.outputToString());
        System.out.println("Adding tail:");
        testLinkedList.addTail(9);
        System.out.print(testLinkedList.outputToString());
        System.out.println("Removing head:");
        testLinkedList.removeHead();
        System.out.print(testLinkedList.outputToString());
        System.out.println("Removing tail:");
        testLinkedList.removeTail();
        System.out.print(testLinkedList.outputToString());
        System.out.println("Peek: " + testLinkedList.peekHead());
        System.out.print(testLinkedList.outputToString());
    }

    static void testStack() {
        System.out.print("\r\nTESTING STACK\r\n");

        Stack testStack = new Stack(42.0);
        testStack.pop();
        testStack.outputToString();
        System.out.println("Empty: " + testStack.isEmpty());
        System.out.println("Adding value to the stack");
        testStack.push(4.0);
        testStack.outputToString();
        System.out.println("Adding value to the stack");
        testStack.push(2.0);
        testStack.outputToString();
        System.out.println("Adding value to the stack");
        testStack.push(8.0);
        testStack.outputToString();
        System.out.println("Popping value from the stack");
        testStack.pop();
        testStack.outputToString();
        System.out.println("Peek: " + testStack.peek());
        testStack.outputToString();
        System.out.println("Empty: " + testStack.isEmpty());
    }

    static void testQueue() {
        System.out.print("\r\nTESTING QUEUE\r\n");

        Queue testQueue = new Queue("Killme");
        testQueue.dequeue();
        testQueue.outputToString();
        System.out.println("Empty: " + testQueue.isEmpty());
        System.out.println("Enqueueing item");
        testQueue.enqueue("First");
        testQueue.outputToString();
        System.out.println("Enqueueing item");
        testQueue.enqueue("Second");
        testQueue.outputToString();
        System.out.println("Enqueueing item");
        testQueue.enqueue("Third");
        testQueue.outputToString();
        System.out.println("Dequeueing item");
        testQueue.dequeue();
        testQueue.outputToString();
        System.out.println("Dequeueing item");
        testQueue.dequeue();
        testQueue.outputToString();
        System.out.println("Enqueueing item");
        testQueue.enqueue("Fourth");
        testQueue.outputToString();
        testQueue.enqueue("Last");
        System.out.println("Enqueueing item");
        testQueue.outputToString();
        System.out.println("Peek: " + testQueue.peek());
        testQueue.outputToString();
        System.out.println("Empty: " + testQueue.isEmpty());
    }
}