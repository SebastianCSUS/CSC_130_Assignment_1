/** Assignment 1 - Proper Stack and Queue - 9/28/2021
 *  Sebastian Jones - Sacramento State University - CSC 130 - Professor Cooke
 */
public class Main {
    public static void main(String[] args){
        Test.testLinkedList();
        Test.testStack();
        Test.testQueue();
        Test.testPool();
    }
}

/** Node Class
 *      Stores an indiscriminate variable and the next node in the chain/list/array.
 */
class Node {
    private Object value;
    private Node next;

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

    public void setValue(Object value) {
        this.value = value;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

/** Doubly linked list
 *      Keeps track of beginning and end of list for O(1) efficiency
 */
class LinkedList {
    private Node head;
    private Node tail;
    private Node pool = new Node(0);            //TODO: Remove value assignment after testing
    private Integer poolSize = 1;
    final Integer poolMax = 9;

    //Constructors
    public LinkedList(){
        Node poolIndex = pool;
        for(int i = 1; i < 10; i++) {                       //starts at 1 because there is already a node made...
            poolIndex.setNext(new Node(i));                   //TODO: Remove value assignment after testing
            poolIndex = poolIndex.getNext();
            poolSize++;
        }
    }
    public LinkedList(Object item) {
        Node poolIndex = pool;
        for(int i = 1; i <= 10; i++) {                       //starts at 1 because there is already a node made...
            poolIndex.setNext(new Node(i));                   //TODO: Remove value assignment after testing
            poolIndex = poolIndex.getNext();
            poolSize = i;
        }
        this.addHead(item);
    }

    String about() {
        return "Author: Sebastian Jones";
    }
    void addHead(Object item) {
        Node node = removePool();
        node.setValue(item);

        if (peekHead() == null) {               //List is empty, make new head
            head = node;
            head.setNext(null);
        } else if (tail == null){               //Only one item in list
            tail = head;
            head = node;
            head.setNext(tail);
        } else {
            Node oldHead = head;                //Not needed but helps readability
            head = node;
            head.setNext(oldHead);
        }
    }
    void addTail(Object item) {
        Node node = removePool();
        node.setValue(item);
        node.setNext(null);

        if (peekHead() == null) {               //List is empty
            head = node;
        } else if (head.getNext() == null) {    //No tail (one item in list)
            tail = node;
            head.setNext(tail);
        } else {
            tail.setNext(node);
            tail = node;
        }
    }
    Object removeHead() {
        if(head == null) {
            return null;
        }
        Node oldHead = head;
        Object returnValue = oldHead.getValue();

        head = head.getNext();
        addPool(oldHead);

        return returnValue;
    }

    void addPool(Node add) {
        if(poolSize <= poolMax) {
            if(pool == null) {                          //TODO: Replace if/else statement with making value null after testing
                add.setValue(9);
            } else {
                add.setValue((Integer) pool.getValue() - 1);
            }
            add.setNext(pool);
            pool = add;
            poolSize++;
            System.out.println("Added node " + pool.getValue() +" to pool");        //TODO: Remove after testing
            return;
        }
        add.setNext(null);
        add.setValue(null);
        System.out.println("Pool at maximum capacity - feeding node to garbage collector");     //TODO: Remove after testing
    }

    Node removePool() {
        if(poolSize == 0) {
            System.out.println("Pool exhausted - making new node");             //TODO: Remove after testing
            return new Node();
        }
        else {
            Node returnNode = pool;
            System.out.println("Removed node " + pool.getValue() + " from pool");       //TODO: Remove after testing
            pool = pool.getNext();
            poolSize--;
            return returnNode;
        }

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
            return "List is empty\r\n";
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

    Double pop() {
        return (Double)stack.removeHead();
    }

    double peek() {
        return (double)stack.peekHead();
    }

    void outputToString() {
        String output = stack.outputToString();
        System.out.print(output);
    }

    boolean isEmpty() {
        return stack.peekHead() == null;
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
        return queue.peekHead() == null;
    }
}

/** Procedures to test other classes - disregard this
 *      I could probably automate this for randomness but that's a lot of work - maybe later
 *          I think this works for now
 */
class Test {
    static void testLinkedList() {
        System.out.print("TESTING LINKED LIST\r\n");
        LinkedList testLinkedList = new LinkedList();
        System.out.println(testLinkedList.about());
        System.out.println("--------------------------------");

        testLinkedList.removeHead();
        System.out.print(testLinkedList.outputToString());
        System.out.println("Adding tail...");
        testLinkedList.addTail(2);
        System.out.print(testLinkedList.outputToString());
        System.out.println("Adding head...");
        testLinkedList.addHead(5);
        System.out.print(testLinkedList.outputToString());
        System.out.println("Adding head...");
        testLinkedList.addHead(7);
        System.out.print(testLinkedList.outputToString());
        System.out.println("Adding tail...");
        testLinkedList.addTail(88);
        System.out.print(testLinkedList.outputToString());
        System.out.println("Adding tail...");
        testLinkedList.addTail(9);
        System.out.print(testLinkedList.outputToString());
        System.out.println("Removing head...");
        testLinkedList.removeHead();
        System.out.print(testLinkedList.outputToString());
        System.out.println("Peek: " + testLinkedList.peekHead());
        System.out.print(testLinkedList.outputToString());
    }

    static void testStack() {
        System.out.print("\r\nTESTING STACK\r\n");
        Stack testStack = new Stack();
        System.out.println(testStack.about());
        System.out.println("--------------------------------");

        testStack.pop();
        testStack.outputToString();
        System.out.println("Empty: " + testStack.isEmpty());
        System.out.println("Adding value to the stack...");
        testStack.push(4.0);
        testStack.outputToString();
        System.out.println("Adding value to the stack...");
        testStack.push(2.0);
        testStack.outputToString();
        System.out.println("Adding value to the stack...");
        testStack.push(8.0);
        testStack.outputToString();
        System.out.println("Popping value from the stack...");
        testStack.pop();
        testStack.outputToString();
        System.out.println("Peek: " + testStack.peek());
        testStack.outputToString();
        System.out.println("Empty: " + testStack.isEmpty());
    }

    static void testQueue() {
        System.out.print("\r\nTESTING QUEUE\r\n");
        Queue testQueue = new Queue();
        System.out.println(testQueue.about());
        System.out.println("--------------------------------");

        testQueue.dequeue();
        testQueue.outputToString();
        System.out.println("Empty: " + testQueue.isEmpty());
        System.out.println("Enqueueing item...");
        testQueue.enqueue("First");
        testQueue.outputToString();
        System.out.println("Enqueueing item...");
        testQueue.enqueue("Second");
        testQueue.outputToString();
        System.out.println("Enqueueing item...");
        testQueue.enqueue("Third");
        testQueue.outputToString();
        System.out.println("Dequeueing item...");
        testQueue.dequeue();
        testQueue.outputToString();
        System.out.println("Dequeueing item...");
        testQueue.dequeue();
        testQueue.outputToString();
        System.out.println("Enqueueing item...");
        testQueue.enqueue("Fourth");
        testQueue.outputToString();
        System.out.println("Enqueueing item...");
        testQueue.enqueue("Last");
        testQueue.outputToString();
        System.out.println("Peek: " + testQueue.peek());
        testQueue.outputToString();
        System.out.println("Empty: " + testQueue.isEmpty());
    }
    static void testPool() {
        System.out.println("\r\nTESTING POOL (Pool maximum size is 10 items)");
        System.out.println("--------------------------------");

        LinkedList testPool = new LinkedList();
        System.out.println("Exhausting pool...");
        for (int i = 0; i <= 11; i++) {
            testPool.addHead(i);
        }
        System.out.println("Overloading pool...");
        for (int i = 11; i >= 0; i--) {
            testPool.removeHead();
        }
    }
}