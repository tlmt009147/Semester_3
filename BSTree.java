/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package countnodes;

/**
 *
 * @author THU
 */
public class BSTree {
    Node root;
    int countNodes(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.left) +countNodes(node.right);
    }
    
    int countNodesAtLevel(Node node, int level) {
        if (node == null) return 0;
        if (level == 1) return 1;
        return countNodesAtLevel(node.left, level - 1) +
                countNodesAtLevel(node.right, level -1);
    }
    void visitNodesAtLevel(Node node, int level) {
        if (node == null) return;
        if (level == 1) System.out.println(node);
        else {
            visitNodesAtLevel(node.left, level-1);
            visitNodesAtLevel(node.right, level-1);
        }
        
    }
}
