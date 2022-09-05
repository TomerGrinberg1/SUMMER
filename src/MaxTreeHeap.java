import java.io.DataOutputStream;
import java.io.IOException;

public class MaxTreeHeap extends Tree {
    protected int heap_size;

    public MaxTreeHeap() {
        this.heap_size = 0;
        this.root = null;
    }

    public MaxTreeHeap(TreeNode treeNode, int length) {
        this.root=treeNode;
        this.heap_size=length;
    }

    private static Boolean isMax(TreeNode r, TreeNode c) {
        return c.key < r.key ? Boolean.FALSE : Boolean.TRUE;
    }

    public static void HeapifyMaxTree(TreeNode node) {
        /*
        if (r == null || (r.left == null && r.right == null)) {
            return;
        }
        if (r.left != null) {
            HeapifyMaxTree(r.left);
            if (isMax(r, r.left)) {
                swap(r, r.left);
                HeapifyMaxTree(r.left);
            }

        }
        if (r.right != null) {
            HeapifyMaxTree(r.right);
            if (isMax(r, r.right)) {
                swap(r, r.right);
                HeapifyMaxTree(r.right);
            }
        }

         */
        if (node == null || (node.left == null ))
            return;

        if(node.right!=null )
        {
            if(node.right.key>node.key && node.right.key>node.left.key){
                swap(node, node.right);
                HeapifyMaxTree(node.right);
            }
            else{
                if(node.left.key>node.key){
                    swap(node, node.left);
                    HeapifyMaxTree(node.left);
                }

            }
        }
        else {
            if (node.right == null && node.left.key > node.key) {
                swap(node, node.left);
                HeapifyMaxTree(node.left);

            }

        }


    }
    public static void HeapifyArray(int[]a,int i){
        int l=2*i+1;int r=2*i+2;
        int bigest;
        int temp = 0;
        if(l<=a.length-1 && a[l]>a[i])
            bigest=l;
        else
            bigest=i;
        if(r<a.length-1 && a[r]>a[bigest])
            bigest=r;
        if(bigest!=i) {
            temp = a[i];
            a[i] = a[bigest];
            a[bigest]=temp;
            HeapifyArray(a,bigest);

        }


    }
    public static void BuildHeap(int []a) {
        for (int i = a.length - 1; i >= 0; i--)
            HeapifyArray(a, i);
    }


        public static MaxTreeHeap BuildHeapT(int[] A) {
            // this.tree.initTree();
            //Heap heap = new Heap(A);
            //MaxTreeHeap maxHeap = new MaxTreeHeap();
            // maxHeap.root = Tree.InitialTree(A, 0);
            BuildHeap(A);
            TreeNode[] B = new TreeNode[A.length + 1];
            B[0] = null;
            for (int i = 0; i < A.length; i++) {
                TreeNode x = new TreeNode(A[i]);
                B[i + 1] = x;
            }
            for (int i = 1; i < B.length; i++) {
                B[i].parent = B[i / 2];
                if (2 * i <= A.length)
                    B[i].left = B[2 * i];
                if ((2 * i) + 1 <= A.length)
                    B[i].right = B[(2 * i) + 1];
            }
            return new MaxTreeHeap(B[1], A.length);
        }
    public int HeapExtractMax() {
        if (this.root == null)
            return Integer.MIN_VALUE;
        if(this.root.left==null)
        {
            heap_size--;
            int max= this.root.key;
            this.root=null;
            return max;
        }

        TreeNode max = new TreeNode(this.root.key);

        // System.out.println("heapsize is ="+this.heap_size);
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
        HeapifyMaxTree(this.root);
        return max.key;
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
        while (parent.key < child.key) {
            swap(parent, child);
            child = parent;
            if(2*i<heap_size)
                parent = find(this.root, heap_size / (2*i));
            i *= 2;
        }
    }


    @Override
    public void printByLayer(DataOutputStream out) throws IOException {
        super.printByLayer(out);
    }
}
