import java.io.DataOutputStream;
import java.io.IOException;

public class MinTreeHeap extends Tree{
    protected int heap_size;
    // private int heap_size;
   // TreeNode[] A_t;
   // Tree tree;
  //  TreeNode tail;
    // TreeNode root;
    //LinkedList<TreeNode> B;

    public MinTreeHeap(int[] A) {


        //  this.A_t=new TreeNode[A.length];
        //if (A[0] >0) {
//             this.tree.root = this.tree.InitialTree(A,0);

         //   this.heap_size = this.tree.treeSize;
        }

  //  }
/*
    void setTail(TreeNode node){


        if(node.parent == null){
            tail = node;
            while(tail.left != null){
                tail = tail.left;
            }
        }

        else if(node.parent.left == node){
            tail = node.parent.right;
            while(tail.left != null){
                tail = tail.left;
            }
        }
        else if(node.parent.right == node){
            setTail(node.parent);
        }
    }
    */
    public MinTreeHeap() {
        this.heap_size = 0;
        this.root=null;
        //this.=new Tree();
        // for (int i=0;i<this.heap_size;i++)
        // this.A_t[i]=new TreeNode(0);
    }

    public MinTreeHeap(TreeNode treeNode, int length) {
        this.root=treeNode;
        this.heap_size=length;
    }

    private static Boolean isMin(TreeNode r, TreeNode c) {
        return c.key < r.key ? Boolean.TRUE : Boolean.FALSE;
    }

    public static void HeapifyArray(int[]a,int i){
        int l=2*i+1;int r=2*i+2;
        int smallest;
        int temp = 0;
        if(l<=a.length-1 && a[l]<a[i])
            smallest=l;
        else
            smallest=i;
        if(r<a.length-1 && a[r]<a[smallest])
            smallest=r;
        if(smallest!=i) {
            temp = a[i];
            a[i] = a[smallest];
            a[smallest]=temp;
            HeapifyArray(a,smallest);

        }


    }

    public static void HeapifyMinTree(TreeNode node) {
       /* if (r == null || (r.left == null && r.right == null)) {
            return;
        }
        //HeapifyMinTree(r);
        TreeNode min;
        if (r.left != null) {
            HeapifyMinTree(r.left);
            if (isMin(r, r.left) && isMin(r.right, r.left)  ) {
                swap(r, r.left);
                HeapifyMinTree(r.left);
            }

        }
        if (r.right != null) {
            HeapifyMinTree(r.right);
            if ( isMin(r, r.right) && isMin(r.left,r.right)) {
                swap(r, r.right);
                HeapifyMinTree(r.right);
            }
        }
*/
        if (node == null || (node.left == null ))
            return;

        if(node.right!=null )
        {
            if(node.right.key<node.key && node.right.key<node.left.key){
                swap(node, node.right);
                HeapifyMinTree(node.right);
            }
            else{
                if(node.left.key<node.key){
                    swap(node, node.left);
                    HeapifyMinTree(node.left);
                }

            }

        }
        else {
            if (node.right == null && node.left.key < node.key) {
                swap(node, node.left);
                HeapifyMinTree(node.left);

            }

        }








        /*

        if (node == null || (node.left == null ))
            return;
        if ( node.left.key < node.key) {
            if (node.right != null && (node.left.key < node.right.key)) {
                swap(node, node.left);
                HeapifyMinTree(node.left);
            } else {

                    swap(node, node.right);
                    HeapifyMinTree(node.right);

            }
        } else {
            if (node.right != null && (node.right.key < node.key)) {
                swap(node, node.right);
                HeapifyMinTree(node.right);
            }
        }
*/
    }
    public static void BuildHeap(int []a){
        for ( int i=a.length-1;i>=0;i--)
            HeapifyArray(a,i);
    }

    public static MinTreeHeap BuildHeapT(int[] A) {
       // this.tree.initTree();
         //Heap heap = new Heap(A);
        BuildHeap(A);
        TreeNode[] B = new TreeNode[A.length+1];
        B[0] = null;
        for (int i = 0; i < A.length; i++) {
            TreeNode x = new TreeNode(A[i]);
            B[i+1] = x;
        }
        for (int i = 1; i < B.length; i++) {
            B[i].parent = B[i /2];
            if(2*i <= A.length)
                B[i].left = B[2*i];
            if((2*i)+1 <= A.length)
                B[i].right = B[(2*i)+1];
        }
        return new MinTreeHeap(B[1],A.length);
    }












        /*
        MinTreeHeap minHeap = new MinTreeHeap();
       // HeapifyArray(A,0);
        minHeap.root=Tree.InitialTree(A,0);
        minHeap.heap_size=A.length;
        //HeapifyMinTree(minHeap.root);
        return minHeap;
*/
       // this.root= this.InitialTree(A,0);
       // HeapifyTree(this.root);
        //heap_size=A.length;
        //System.out.println(heap_size);
        //UnboundedTree T=new UnboundedTree();
        //  for (int i = A.length-1; i > 0; i--) {
        // HeapifyTree(T.root);

        //   }
        //return heap;



    public int HeapExtractMin() {
       if (this.root == null)
            return Integer.MAX_VALUE;
        if(this.root.left==null)
        {
            heap_size--;
            int min= this.root.key;
            this.root=null;
            return min;
        }
        TreeNode min = new TreeNode(this.root.key);

        //System.out.println("heapsize is ="+this.heap_size);
        TreeNode DeepestRightmost= this.find(this.root,this.heap_size);
        TreeNode ParentOfDeepRight = this.find(this.root, this.heap_size / 2);
        //System.out.println(DeepestRighmost.key);
        this.root.setKey(DeepestRightmost.key);
        if(heap_size%2==0) {
            ParentOfDeepRight.left=null;
        }
        else
            ParentOfDeepRight.right=null;


        this.heap_size = this.heap_size - 1;
        HeapifyMinTree(this.root);
        return min.key;
    }

    public static void Heap_Decrease_Key(MinTreeHeap A, int i, int k) {
//
//        if (k > A.A_t[i].key)
//            return;
//        A.A_t[i].key = k;
//        while (i > 0 && A.A_t[i].key < A.A_t[floorDiv(i, 2)].key) {
//            TreeNode temp = A.A_t[i];
//            A.A_t[i] = A.A_t[floorDiv(i, 2)];
//            A.A_t[floorDiv(i, 2)] = temp;
//            i = floorDiv(i, 2);
//        }

    }

    public  void HeapInsert( int k) {
        if (this.root == null){
            this.root = new TreeNode(k);
            heap_size++;
        }
        this.heap_size++;
        TreeNode parent = this.find(this.root, heap_size / 2);
        if (heap_size % 2 == 0)
            parent.left = new TreeNode(k);
        else
            parent.right = new TreeNode(k);
        TreeNode child = this.find(this.root, heap_size );
        int i = 2;
        while (parent.key > child.key) {
            swap(parent, child);
            child = parent;
            if(2*i<heap_size)
                 parent = find(this.root, heap_size / (2*i));
            i *= 2;
            }
        }


       // swap(this.root,this.find(this.root,heap_size));
        // Heap.Heap_Insert(this.minHeap,k);
      //  HeapifyTree(this.tree.find(this.tree.root,heap_size));
       // HeapifyMinTree(this.root);
        //this.heap_size++;
        //Heap.Heap_Insert(this.minHeap,newNode);


//        int s = A.heap_size + 1;
//        A.A_t[s] = x;
//        A.A_t[s].key = Integer.MAX_VALUE;
//        A.heap_size = s;
//        Heap_Decrease_Key(A, s, x.key);

    @Override
    public void printByLayer(DataOutputStream out) throws IOException {
        super.printByLayer(out);
    }


}


