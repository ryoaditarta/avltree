package srcs;
import java.util.*; 
class Node{
    int data; 
    Node leftChild; 
    Node rightChild; 
    int height = 1; 

    Node(int n){
        data = n; 
    }
}

class avltree{
    Node root;

    public void insert(int value) {
        root = insertNode(root, value);
    }

    private Node insertNode(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }
        if (value < node.data) {
            node.leftChild = insertNode(node.leftChild, value);
        } else if (value > node.data) {
            node.rightChild = insertNode(node.rightChild, value);
        } else return node;

        updateHeight(node); 
        return rotasi(node); 
    }

    public void updateHeight(Node node){
        int maxHeight = Math.max(height(node.leftChild),height(node.rightChild));
        node.height = maxHeight+1; 
    }

    public Node rotasi(Node node){
        int balance = countBalanceFactor(node);

        if(balance > 1){
            if(countBalanceFactor(node.leftChild) < 0){
                node.leftChild = rotateLeft(node.leftChild); 
            }
            
            return rotateRight(node); 
        }

        if(balance < -1){
            if(countBalanceFactor(node.rightChild) > 0){
                node.rightChild = rotateRight(node.rightChild); 
            }
            return rotateLeft(node); 
        }

        return node;
    }

    private int height(Node node) {
        return node != null ? node.height : 0; 
    }

    private int countBalanceFactor(Node node) {
        return node != null ? height(node.leftChild) - height(node.rightChild) : 0; 
    }

    private Node rotateRight(Node node)  
    {  
        Node anakKiri = node.leftChild; 
        Node anakDalam = anakKiri.rightChild; 

        anakKiri.rightChild = node; 
        node.leftChild = anakDalam; 

        updateHeight(node);
        updateHeight(anakKiri);
        return anakKiri; 
    }  

    private Node rotateLeft(Node node){
        Node anakKanan = node.rightChild; 
        Node anakDalam = anakKanan.leftChild;

        anakKanan.leftChild = node;
        node.rightChild = anakDalam; 

        updateHeight(node); 
        updateHeight(anakKanan);
        return anakKanan; 
    }

    public void preorder(){
        if(root==null) System.out.println("empty!");
        else prerec(root); 
        System.out.println();
    }

    public void prerec(Node cur){
        if(cur==null)return; 
        System.out.print(cur.data+" "); 
        prerec(cur.leftChild); 
        prerec(cur.rightChild); 

    }

    public void inorder(){
        if(root == null) System.out.println("empty!");
        else inrec(root); 
        System.out.println();
    }

    public void inrec(Node cur){
        if(cur==null) return; 
        inrec(cur.leftChild); 
        System.out.print(cur.data+" "); 
        inrec(cur.rightChild);
    }

    public void postorder(){
        if(root == null) System.out.println("empty!");
        else postrec(root); 
        System.out.println();
    }

    public void postrec(Node cur){
        if(cur==null) return; 
        postrec(cur.leftChild); 
        postrec(cur.rightChild); 
        System.out.print(cur.data+" "); 
    }

    public void level(){
        System.out.println(root.height);
    }

}

public class pohonavl{
    public static void main(String[] args){

        Scanner input = new Scanner(System.in); 
        avltree avl = new avltree(); 
        
        while(true){
            String temp = input.nextLine(); 
            String[] cmd = temp.split(" "); 
            switch(cmd[0]){
                case "insert":
                    avl.insert(Integer.parseInt(cmd[1])); 
                break; 
                case "print":
                    if(cmd[1].equals("preorder")) avl.preorder();
                    else if(cmd[1].equals("inorder")) avl.inorder();
                    else if(cmd[1].equals("postorder")) avl.postorder();
                    else if(cmd[1].equals("level"))avl.level();;
                break; 
                case "exit":
                    input.close(); 
                    return; 
                default: 
                    System.out.println("Perintah salah!");
            }
        }
    }
}
