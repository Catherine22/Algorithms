package com.catherine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.catherine.data_type.MyArrayList;
import com.catherine.data_type.MyLinkedList;
import com.catherine.data_type.Operator;
import com.catherine.data_type.Search;
import com.catherine.data_type.Sequence;
import com.catherine.dictionary.HashingHelper;
import com.catherine.dictionary.data.Student;
import com.catherine.dictionary.functions.CollisionMode;
import com.catherine.dictionary.functions.Fold;
import com.catherine.dictionary.functions.HashingTemplate;
import com.catherine.dictionary.functions.Mod;
import com.catherine.dictionary.functions.MidSquare;
import com.catherine.dictionary.functions.Remainder;
import com.catherine.dictionary.functions.RotateAndFold;
import com.catherine.dictionary.functions.RotateAndXORFold;
import com.catherine.dictionary.functions.SelectingDigits;
import com.catherine.dictionary.functions.XORFold;
import com.catherine.graphs.DirectedGraph;
import com.catherine.graphs.DirectedGraph.Vertex;
import com.catherine.priority_queue.LeftistHeap;
import com.catherine.priority_queue.MyCompleteBinaryHeap;
import com.catherine.priority_queue.MyCompleteBinaryHeap.Structure;
import com.catherine.priority_queue.PriorityQueueBinTreeImpl;
import com.catherine.sort.BaseSort;
import com.catherine.sort.BubbleSort;
import com.catherine.sort.HeapSort;
import com.catherine.sort.InsertionSort;
import com.catherine.sort.MergeSort;
import com.catherine.sort.QuickSort;
import com.catherine.sort.SelectionSort;
import com.catherine.sort.SortableStackPermutation;
import com.catherine.string.StringUtils;
import com.catherine.trees.Callback;
import com.catherine.trees.MyAVLTree;
import com.catherine.trees.MyBTree;
import com.catherine.trees.MyBinarySearchTree;
import com.catherine.trees.MyBinaryTree;
import com.catherine.trees.MyRedBlackBST;
import com.catherine.trees.MySplayTree;
import com.catherine.trees.MyBinaryTree.Order;
import com.catherine.trees.nodes.Node;
import com.catherine.turing_machine.TuringMachine;
import com.catherine.utils.Analysis;
import com.catherine.utils.NumberSystem;
import com.catherine.utils.Others;
import com.catherine.utils.TrackLog;
import com.catherine.security.Algorithm;
import com.catherine.security.CertificatesManager;
import com.catherine.security.CipherKit;
import com.catherine.security.DESCallback;
import com.catherine.security.JwsHelper;
import com.catherine.security.KeySet;
import com.catherine.security.KeystoreManager;
import com.catherine.security.MessageDigestKit;
import com.catherine.security.RSACallback;
import com.catherine.security.jws_object.AttestationResult;

public class Main {

	private static int[] input1 = new int[] { 3, 5, 7, 1, 4, 2, 10, 4, -10, 3,
			5, 7, 1, 4, 2, 10, 4, -10, 3, 5, 7, 1, 4, 2, 10, 4, -10, 3, 5, 7,
			1, 4, 2, 10, 4, -10 };
	private static int[] input2 = new int[] { 38, 29, 28, 11, 4, 5, 2 };
	private static int[] input6 = new int[] { 1, 4, 1, 1, 7, 3, 64, 5, 23, 12,
			14, 10 };

	public static void main(String[] args) {
		// compareStringSorting();
		compareIntSorting();
		// testHailstone();
		// testTuringMachine();
		// testSequence();
		// testSearch();
		// testArrayList();
		// testLinkedList();
		// testStack();
		// testDirectedGraph();
		// testBinaryTree();
		// testBST();
		// testAVLTree();
		// testRedBlackBST();
		// testSplayTree();
		// testBTree();
		// testPQVector();
		// testPQBinTree();
		// testLeftistHeaps();
		// testString();
		// testHash();
		// testCryptography();
		// testJWS();
	}

	public static void testHash() {
		CollisionMode doNothing = new CollisionMode.Builder().build();
		CollisionMode linearProbing = new CollisionMode.Builder()
				.mode(CollisionMode.LINEAR_PROBING).spareBuckets(3).build();
		CollisionMode quadraticPobing = new CollisionMode.Builder()
				.mode(CollisionMode.QUADRATIC_PROBING).spareBuckets(3).mod(31)
				.build();
		CollisionMode fermatQuadraticPobing = new CollisionMode.Builder()
				.mode(CollisionMode.FERMAT_QUADRATIC_PROBING).spareBuckets(3)
				.mod(31).build();

		HashingHelper hashingHelper = new HashingHelper("students_raw",
				doNothing);
		hashingHelper.createRandomTable(100, 0.75f, 30, 336, true);

		// Do not fix collisions**************************
		HashingTemplate remainder = new Remainder(17, doNothing);
		remainder.hash(hashingHelper.getStudent());
		remainder.analyse(hashingHelper.getTableList(),
				hashingHelper.getStudent(), remainder.getTableList(),
				remainder.getStudent());

		Mod mod = new Mod(2, 14, 17, doNothing);
		mod.hash(hashingHelper.getStudent());
		mod.analyse(hashingHelper.getTableList(), hashingHelper.getStudent(),
				mod.getTableList(), mod.getStudent());

		HashingTemplate sd = new SelectingDigits(doNothing);
		sd.hash(hashingHelper.getStudent());
		sd.analyse(hashingHelper.getTableList(), hashingHelper.getStudent(),
				sd.getTableList(), sd.getStudent());

		MidSquare ms = new MidSquare(doNothing);
		ms.hash(hashingHelper.getStudent());
		ms.analyse(hashingHelper.getTableList(), hashingHelper.getStudent(),
				ms.getTableList(), ms.getStudent());

		Fold fold = new Fold(2, doNothing);
		fold.hash(hashingHelper.getStudent());
		fold.analyse(hashingHelper.getTableList(), hashingHelper.getStudent(),
				fold.getTableList(), fold.getStudent());

		RotateAndFold raf = new RotateAndFold(2, doNothing);
		raf.hash(hashingHelper.getStudent());
		raf.analyse(hashingHelper.getTableList(), hashingHelper.getStudent(),
				raf.getTableList(), raf.getStudent());

		XORFold xorf = new XORFold(2, doNothing);
		xorf.hash(hashingHelper.getStudent());
		xorf.analyse(hashingHelper.getTableList(), hashingHelper.getStudent(),
				xorf.getTableList(), xorf.getStudent());

		RotateAndXORFold raxorf = new RotateAndXORFold(2, doNothing);
		raxorf.hash(hashingHelper.getStudent());
		raxorf.analyse(hashingHelper.getTableList(),
				hashingHelper.getStudent(), raxorf.getTableList(),
				raxorf.getStudent());

		// Fix collisions (Linear Probing)**************************
		// HashingTemplate remainderL = new Remainder(17, linearProbing);
		// remainderL.hash(hashingHelper.getStudent());
		// remainderL.analyse(hashingHelper.getTableList(),
		// hashingHelper.getStudent(), remainderL.getTableList(),
		// remainderL.getStudent());
		//
		// Mod modL = new Mod(2, 14, 17, linearProbing);
		// modL.hash(hashingHelper.getStudent());
		// modL.analyse(hashingHelper.getTableList(),
		// hashingHelper.getStudent(), modL.getTableList(), modL.getStudent());
		//
		// SelectingDigits sdL = new SelectingDigits(linearProbing);
		// sdL.hash(hashingHelper.getStudent());
		// sdL.analyse(hashingHelper.getTableList(), hashingHelper.getStudent(),
		// sdL.getTableList(), sdL.getStudent());
		//
		// MidSquare msL = new MidSquare(linearProbing);
		// msL.hash(hashingHelper.getStudent());
		// msL.analyse(hashingHelper.getTableList(), hashingHelper.getStudent(),
		// msL.getTableList(), msL.getStudent());
		//
		// Fold foldL = new Fold(2, linearProbing);
		// foldL.hash(hashingHelper.getStudent());
		// foldL.analyse(hashingHelper.getTableList(),
		// hashingHelper.getStudent(), foldL.getTableList(),
		// foldL.getStudent());
		//
		// RotateAndFold rafL = new RotateAndFold(2, linearProbing);
		// rafL.hash(hashingHelper.getStudent());
		// rafL.analyse(hashingHelper.getTableList(),
		// hashingHelper.getStudent(), rafL.getTableList(), rafL.getStudent());
		//
		// XORFold xorfL = new XORFold(2, linearProbing);
		// xorfL.hash(hashingHelper.getStudent());
		// xorfL.analyse(hashingHelper.getTableList(),
		// hashingHelper.getStudent(), xorfL.getTableList(),
		// xorfL.getStudent());
		//
		// RotateAndXORFold raxorfL = new RotateAndXORFold(2, linearProbing);
		// raxorfL.hash(hashingHelper.getStudent());
		// raxorfL.analyse(hashingHelper.getTableList(),
		// hashingHelper.getStudent(), raxorfL.getTableList(),
		// raxorfL.getStudent());

		// Fix collisions (Quadratic Probing)**************************
		// Remainder remainderQ = new Remainder(17, quadraticPobing);
		// remainderQ.hash(hashingHelper.getStudent());
		// remainderQ.analyse(hashingHelper.getTableList(),
		// hashingHelper.getStudent(), remainderQ.getTableList(),
		// remainderQ.getStudent());
		//
		// Mod modQ = new Mod(2, 14, 17, quadraticPobing);
		// modQ.hash(hashingHelper.getStudent());
		// modQ.analyse(hashingHelper.getTableList(),
		// hashingHelper.getStudent(), modQ.getTableList(), modQ.getStudent());
		//
		// SelectingDigits sdQ = new SelectingDigits(quadraticPobing);
		// sdQ.hash(hashingHelper.getStudent());
		// sdQ.analyse(hashingHelper.getTableList(), hashingHelper.getStudent(),
		// sdQ.getTableList(), sdQ.getStudent());
		//
		// MidSquare msQ = new MidSquare(quadraticPobing);
		// msQ.hash(hashingHelper.getStudent());
		// msQ.analyse(hashingHelper.getTableList(), hashingHelper.getStudent(),
		// msQ.getTableList(), msQ.getStudent());
		//
		// Fold foldQ = new Fold(2, quadraticPobing);
		// foldQ.hash(hashingHelper.getStudent());
		// foldQ.analyse(hashingHelper.getTableList(),
		// hashingHelper.getStudent(), foldQ.getTableList(),
		// foldQ.getStudent());
		//
		// RotateAndFold rafQ = new RotateAndFold(2, quadraticPobing);
		// rafQ.hash(hashingHelper.getStudent());
		// rafQ.analyse(hashingHelper.getTableList(),
		// hashingHelper.getStudent(), rafQ.getTableList(), rafQ.getStudent());
		//
		// XORFold xorfQ = new XORFold(2, quadraticPobing);
		// xorfQ.hash(hashingHelper.getStudent());
		// xorfQ.analyse(hashingHelper.getTableList(),
		// hashingHelper.getStudent(), xorfQ.getTableList(),
		// xorfQ.getStudent());
		//
		// RotateAndXORFold raxorfQ = new RotateAndXORFold(2, quadraticPobing);
		// raxorfQ.hash(hashingHelper.getStudent());
		// raxorfQ.analyse(hashingHelper.getTableList(),
		// hashingHelper.getStudent(), raxorfQ.getTableList(),
		// raxorfQ.getStudent());

		// Fix collisions (Fermat Quadratic Probing)**************************
		Remainder remainderF = new Remainder(17, fermatQuadraticPobing);
		remainderF.hash(hashingHelper.getStudent());
		remainderF.analyse(hashingHelper.getTableList(),
				hashingHelper.getStudent(), remainderF.getTableList(),
				remainderF.getStudent());

		Mod modF = new Mod(2, 14, 17, fermatQuadraticPobing);
		modF.hash(hashingHelper.getStudent());
		modF.analyse(hashingHelper.getTableList(), hashingHelper.getStudent(),
				modF.getTableList(), modF.getStudent());

		SelectingDigits sdF = new SelectingDigits(fermatQuadraticPobing);
		sdF.hash(hashingHelper.getStudent());
		sdF.analyse(hashingHelper.getTableList(), hashingHelper.getStudent(),
				sdF.getTableList(), sdF.getStudent());

		MidSquare msF = new MidSquare(fermatQuadraticPobing);
		msF.hash(hashingHelper.getStudent());
		msF.analyse(hashingHelper.getTableList(), hashingHelper.getStudent(),
				msF.getTableList(), msF.getStudent());

		Fold foldF = new Fold(2, fermatQuadraticPobing);
		foldF.hash(hashingHelper.getStudent());
		foldF.analyse(hashingHelper.getTableList(), hashingHelper.getStudent(),
				foldF.getTableList(), foldF.getStudent());

		RotateAndFold rafF = new RotateAndFold(2, fermatQuadraticPobing);
		rafF.hash(hashingHelper.getStudent());
		rafF.analyse(hashingHelper.getTableList(), hashingHelper.getStudent(),
				rafF.getTableList(), rafF.getStudent());

		XORFold xorfF = new XORFold(2, fermatQuadraticPobing);
		xorfF.hash(hashingHelper.getStudent());
		xorfF.analyse(hashingHelper.getTableList(), hashingHelper.getStudent(),
				xorfF.getTableList(), xorfF.getStudent());

		RotateAndXORFold raxorfF = new RotateAndXORFold(2,
				fermatQuadraticPobing);
		raxorfF.hash(hashingHelper.getStudent());
		raxorfF.analyse(hashingHelper.getTableList(),
				hashingHelper.getStudent(), raxorfF.getTableList(),
				raxorfF.getStudent());
	}

	public static void testString() {
		final char[] DNAs = { 'A', 'T', 'C', 'G' };
		Random random = new Random();
		int SIZE = 100;
		int SUB_SIZE = 4;

		String str;
		String subStr;
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i < SIZE; i++) {
			sBuilder.append(DNAs[random.nextInt(4)]);
			random = new Random();
		}
		str = sBuilder.toString();
		sBuilder.delete(0, sBuilder.length());

		for (int i = 0; i < SUB_SIZE; i++) {
			sBuilder.append(DNAs[random.nextInt(4)]);
			random = new Random();
		}
		subStr = sBuilder.toString();
		sBuilder.delete(0, sBuilder.length());

		// str = "???????????????????????????????????????";
		// str = "????????????????????????????????????";
		// str =
		// "000100001***********chin**chilla************chinchilla**********************************************";
		// str =
		// "CCCGGGCTAGTACTAATATCCGGCGATACGCCTTCGGGGACGAACGTCGGTCGAATCAGATCCAACAAGCGATCTTTGGCAGACCTGTAACAACAGTTTAGATCCGTAGTCGTTCGACGTTAGTCACTACAACAAAAAGTCAAGCTAGCGTGTCTGCTTTTCGAGTGAAAGTATGCGCAGTGGAGTAGGTGATGCTCTGG";
		int f = 0;
		int d = 25;
		int t = d;
		System.out.println("String:");
		while (f < str.length()) {
			System.out.println(str.substring(f, t));
			f = t;
			t += d;
		}

		// subStr = "????????????????????????";
		// subStr = "00001";
		// subStr = "CTTC";
		System.out.println("\nSubString:\n".concat(subStr));

		int i = StringUtils.indexOfBF(str, subStr);
		System.out.println(String.format("\nindexOfBF:%d", i));

		i = StringUtils.indexOfKMP(str, subStr);
		System.out.println(String.format("\nindexOfKMP:%d", i));

		i = StringUtils.indexOfBM_BC(str, subStr);
		System.out.println(String.format("\nindexOfBM_BC:%d", i));

		i = StringUtils.indexOfBM_GS(str, subStr);
		System.out.println(String.format("\nindexOfBM_GS:%d", i));
	}

	public static void testLeftistHeaps() {
		Random random = new Random();
		int SIZE = 1 + random.nextInt(15);
		List<Integer> list = new ArrayList<>();

		// merge
		PriorityQueueBinTreeImpl<Integer> a = new PriorityQueueBinTreeImpl<>(0);
		for (int i = 0; i < SIZE; i++) {
			list.add(random.nextInt(1000));
			random = new Random();
		}

		a.heapify(list);
		System.out.println("a:");
		printList("raw data1", list);
		a.traverseLevel();

		list.clear();
		PriorityQueueBinTreeImpl<Integer> b = new PriorityQueueBinTreeImpl<>(0);
		SIZE = 1 + random.nextInt(15);
		for (int i = 0; i < SIZE; i++) {
			list.add(random.nextInt(1000));
			random = new Random();
		}

		b.heapify(list);
		System.out.println("b:");
		printList("raw data2", list);
		b.printTree();

		System.out.println(String.format("merge a(%d) and b(%d)", a.size(),
				b.size()));
		LeftistHeap<Integer> lh1 = new LeftistHeap<>();
		lh1.merge(a.getRoot(), b.getRoot());
		lh1.printTree();

		// insert
		list.clear();
		for (int i = 0; i < SIZE; i++) {
			list.add(random.nextInt(1000));
			random = new Random();
		}
		LeftistHeap<Integer> lh2 = new LeftistHeap<>();
		for (int i = 0; i < list.size(); i++) {
			lh2.insert(list.get(i));
		}
		System.out.println("merge:");
		printList("raw data", list);
		lh2.printTree();

		// delete
		random = new Random();
		int pos = 0 + random.nextInt(list.size() - 1);
		System.out.println("Remove " + list.get(pos));
		lh2.remove(list.get(pos));
		lh2.printTree();
	}

	public static void testPQBinTree() {
		Random random = new Random();
		int SIZE = 1 + random.nextInt(15);
		int TEXT_LEN = 5;
		StringBuilder sBuilder = new StringBuilder();
		String[] raw = new String[SIZE];

		// root
		for (int j = 0; j < TEXT_LEN; j++) {
			sBuilder.append((char) ('A' + random.nextInt(26)));
		}
		MyCompleteBinaryHeap<String> pq = new MyCompleteBinaryHeap<>(
				sBuilder.toString());

		raw[0] = sBuilder.toString();
		sBuilder.delete(0, TEXT_LEN);
		random = new Random();

		// random elements
		for (int i = 1; i < SIZE; i++) {
			for (int j = 0; j < TEXT_LEN; j++) {
				sBuilder.append((char) ('A' + random.nextInt(26)));
			}
			pq.insert(sBuilder.toString());
			raw[i] = sBuilder.toString();
			sBuilder.delete(0, TEXT_LEN);
			random = new Random();
		}
		printArray("raw data", raw);
		pq.printTree();

		System.out.println("\nDelete max");
		pq.delMax();
		pq.printTree();

		// test2
		List<Integer> list = new ArrayList<>();
		list.add(random.nextInt(1000));
		MyCompleteBinaryHeap<Integer> pq2 = new MyCompleteBinaryHeap<>(
				list.get(0));
		for (int i = 1; i < SIZE; i++) {
			list.add(random.nextInt(1000));
			random = new Random();
		}

		printList("raw data", list);

		pq2.heapify(list);
		pq2.printTree();

		System.out.println("\ndelete max");
		pq2.delMax();
		pq2.printTree();
	}

	public static void testPQVector() {
		MyCompleteBinaryHeap<String> pq = new MyCompleteBinaryHeap<>();
		Random random = new Random();
		int SIZE = 1 + random.nextInt(15);
		int TEXT_LEN = 5;
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < TEXT_LEN; j++) {
				sBuilder.append((char) ('A' + random.nextInt(26)));
			}
			pq.insert(sBuilder.toString());
			sBuilder.delete(0, TEXT_LEN);
			random = new Random();
		}

		printIterator(pq.iterator());
		pq.printTree();

		System.out.println("\nDelete max");
		pq.delMax();
		printIterator(pq.iterator());
		pq.printTree();

		MyCompleteBinaryHeap<String> pq2 = new MyCompleteBinaryHeap<>();
		List<String> list = new ArrayList<>();
		SIZE = 1 + random.nextInt(15);
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < TEXT_LEN; j++) {
				sBuilder.append((char) ('A' + random.nextInt(26)));
			}
			list.add(sBuilder.toString());
			sBuilder.delete(0, TEXT_LEN);
			random = new Random();
		}
		System.out.println("\nheapify");
		pq2.heapify(list);
		printIterator(pq2.iterator());
		pq2.printTree();
	}

	public static void testBTree() {
		Random random = new Random();
		int root = 0 + random.nextInt(1000);
		int m = 2 + random.nextInt(10);
		MyBTree<Integer> myBTree = new MyBTree<>(m, root);
		myBTree.printInfo();

		int SIZE = 1 + random.nextInt(20);
		int[] history = new int[SIZE];
		history[0] = root;
		for (int i = 1; i < SIZE; i++) {
			history[i] = 0 + random.nextInt(1000);
			myBTree.insert(history[i]);
			random = new Random();
		}

		System.out.println(String.format("\nGenerate a B-tree, m=%d, size=%d",
				m, SIZE));
		System.out.println(myBTree.toString());
		int r = 0 + random.nextInt(SIZE - 1);
		System.out.println("\nremove history[" + r + "]:" + history[r]);
		myBTree.remove(history[r]);
		System.out.println(myBTree.toString());

		r = 0 + random.nextInt(SIZE - 1);
		System.out.println("\nremove history[" + r + "]:" + history[r]);
		myBTree.remove(history[r]);
		System.out.println(myBTree.toString());

	}

	public static void testSplayTree() {
		MySplayTree<Integer> mySplayTree1 = new MySplayTree<Integer>(641);
		mySplayTree1.add(468);
		mySplayTree1.add(777);
		mySplayTree1.add(448);
		mySplayTree1.add(507);
		mySplayTree1.add(743);
		mySplayTree1.add(21);
		mySplayTree1.add(486);
		mySplayTree1.add(527);
		mySplayTree1.add(1);
		mySplayTree1.add(285);
		mySplayTree1.add(495);
		mySplayTree1.add(69);
		mySplayTree1.add(333);
		mySplayTree1.add(266);
		mySplayTree1.add(400);
		mySplayTree1.traverseLevel();
		mySplayTree1.search(333);
		mySplayTree1.traverseLevel();

		// the worst case
		MySplayTree<Integer> mySplayTree2 = new MySplayTree<Integer>(90);
		mySplayTree2.add(80);
		mySplayTree2.add(70);
		mySplayTree2.add(60);
		mySplayTree2.add(50);
		mySplayTree2.add(40);
		mySplayTree2.add(30);
		mySplayTree2.add(20);
		mySplayTree2.add(10);
		mySplayTree2.add(0);

		// System.out.println("Searching with ordinary rotation in the worst
		// case");
		// for (int i = 0; i < 100; i += 10) {
		// System.out.println(String.format("rotate %d", i));
		// mySplayTree2.search(i, false);
		// mySplayTree2.traverseLevel();
		// }
		// System.out.println("");
		// System.out.println("Searching with efficient rotation in the worst
		// case");
		// for (int i = 0; i < 100; i += 10) {
		// System.out.println(String.format("rotate %d", i));
		// mySplayTree2.search(i);
		// mySplayTree2.traverseLevel();
		// }

		// add
		mySplayTree2.add(15);
		mySplayTree2.traverseLevel();

		// delete
		mySplayTree1.remove(468);
		mySplayTree1.traverseLevel();
	}

	private static String attestationJws = "eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlFaURDQ0EzQ2dBd0lCQWdJSU5CY1JCYkpDSWxBd0RRWUpLb1pJaHZjTkFRRUxCUUF3U1RFTE1Ba0dBMVVFQmhNQ1ZWTXhFekFSQmdOVkJBb1RDa2R2YjJkc1pTQkpibU14SlRBakJnTlZCQU1USEVkdmIyZHNaU0JKYm5SbGNtNWxkQ0JCZFhSb2IzSnBkSGtnUnpJd0hoY05NVGN3TlRFM01UQTBNRE00V2hjTk1UY3hNakkzTURBd01EQXdXakJzTVFzd0NRWURWUVFHRXdKVlV6RVRNQkVHQTFVRUNBd0tRMkZzYVdadmNtNXBZVEVXTUJRR0ExVUVCd3dOVFc5MWJuUmhhVzRnVm1sbGR6RVRNQkVHQTFVRUNnd0tSMjl2WjJ4bElFbHVZekViTUJrR0ExVUVBd3dTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUlJQklqQU5CZ2txaGtpRzl3MEJBUUVGQUFPQ0FROEFNSUlCQ2dLQ0FRRUF2L1dPZFpURlJlN29FUFhuUkNWOVNlbWxrNGpnckZSd0tFRng4d2FRTUMrU3NzSld5dUhpSHNNdEY2MDdOcFR1MCttbFBKOEM5TkhhbmtkUElzS3RLNmJ0emMreDBlc2c1VS9JUkQ0K2JRNVpSSDBrT1BxMmZpd1g1WmJnZDUrOFIzOWIyYkxmV0dDMmJkV1lxbHBvTUs1bXhFWW1BVVdIb0J4M2JHUldCR05BMi8vNHpaS0xqc0lFSFdFYksxOE5Kb0w3VlJOaExENlkvcmVXVElDdUNHdzVraERvbEJwYUw0R0xCakZvSEVBelNoZjFlUzhuYUFFcGpzQ3Z5ZWFCVzM0ZGJhUFJadFRIVWFBRDRtYXZCZ2hta0Z2eVRFL293SUJFa0pVakliRzFpUDlndnVzaEJhTmxjOHIway9tR1k1am1uNTZhVWRVZ1JnWjFwditqRFFJREFRQUJvNElCVHpDQ0FVc3dIUVlEVlIwbEJCWXdGQVlJS3dZQkJRVUhBd0VHQ0NzR0FRVUZCd01DTUIwR0ExVWRFUVFXTUJTQ0VtRjBkR1Z6ZEM1aGJtUnliMmxrTG1OdmJUQm9CZ2dyQmdFRkJRY0JBUVJjTUZvd0t3WUlLd1lCQlFVSE1BS0dIMmgwZEhBNkx5OXdhMmt1WjI5dloyeGxMbU52YlM5SFNVRkhNaTVqY25Rd0t3WUlLd1lCQlFVSE1BR0dIMmgwZEhBNkx5OWpiR2xsYm5Sek1TNW5iMjluYkdVdVkyOXRMMjlqYzNBd0hRWURWUjBPQkJZRUZCSitWS3BMSlNaK3VxOTEraW9ReHRTNzlMVzdNQXdHQTFVZEV3RUIvd1FDTUFBd0h3WURWUjBqQkJnd0ZvQVVTdDBHRmh1ODltaTFkdldCdHJ0aUdycGFnUzh3SVFZRFZSMGdCQm93R0RBTUJnb3JCZ0VFQWRaNUFnVUJNQWdHQm1lQkRBRUNBakF3QmdOVkhSOEVLVEFuTUNXZ0k2QWhoaDlvZEhSd09pOHZjR3RwTG1kdmIyZHNaUzVqYjIwdlIwbEJSekl1WTNKc01BMEdDU3FHU0liM0RRRUJDd1VBQTRJQkFRQ1hoMjdtNG11ZEs3NEwxM3FSWml5K0Jrc2ZudmJzL0dEaW9ra0FwdDVveUEvMk10TTFFMGxHY2tEL2NjVWpuNUF1RWx5N3FkdGJnM2NOTk9BbGlWemxnVWFHWTZXNldUWnhrNGl2UVcwbmpwOUFrWkU3Y2N1VDJVU000MEp0dS9WQWxGYUZQV1N3RXBoa3J6VUNjQ2M3cjFTSGpya1FxekRGUnc5dmV0V1VZMWFJZmw3VklGcG1RdmZNMnV3TFlneXRsblBMbHNsMVdrMFBVbWNsc2lEMUg3MmtLelZLeE5ySkFuWk1CeC83SXBKY0Q3alhNTGhneGluK3FVUFBtNzF1M2pxS2VSZ0tVK3FlTjcvSnJOb1dlSXIrR1krbTE0VFljSzhhWkNad3p4VkczeXdVeUI3U1ZzR0tRTEtGRk1USDF1T3lSL1lnSjN2cmgyT1QvMXdiIiwiTUlJRDhEQ0NBdGlnQXdJQkFnSURBanFTTUEwR0NTcUdTSWIzRFFFQkN3VUFNRUl4Q3pBSkJnTlZCQVlUQWxWVE1SWXdGQVlEVlFRS0V3MUhaVzlVY25WemRDQkpibU11TVJzd0dRWURWUVFERXhKSFpXOVVjblZ6ZENCSGJHOWlZV3dnUTBFd0hoY05NVFV3TkRBeE1EQXdNREF3V2hjTk1UY3hNak14TWpNMU9UVTVXakJKTVFzd0NRWURWUVFHRXdKVlV6RVRNQkVHQTFVRUNoTUtSMjl2WjJ4bElFbHVZekVsTUNNR0ExVUVBeE1jUjI5dloyeGxJRWx1ZEdWeWJtVjBJRUYxZEdodmNtbDBlU0JITWpDQ0FTSXdEUVlKS29aSWh2Y05BUUVCQlFBRGdnRVBBRENDQVFvQ2dnRUJBSndxQkhkYzJGQ1JPZ2FqZ3VEWVVFaThpVC94R1hBYWlFWis0SS9GOFluT0llNWEvbUVOdHpKRWlhQjBDMU5QVmFUT2dtS1Y3dXRaWDhiaEJZQVN4RjZVUDd4YlNEajBVL2NrNXZ1UjZSWEV6L1JURGZSSy9KOVUzbjIrb0d0dmg4RFFVQjhvTUFOQTJnaHpVV3gvL3pvOHB6Y0dqcjFMRVFUcmZTVGU1dm44TVhIN2xOVmc4eTVLcjBMU3krckVhaHF5ekZQZEZVdUxIOGdaWVIvTm5hZytZeXVFTldsbGhNZ1p4VVlpK0ZPVnZ1T0FTaERHS3V5Nmx5QVJ4em1aRUFTZzhHRjZsU1dNVGxKMTRyYnRDTW9VL000aWFyTk96MFlEbDVjRGZzQ3gzbnV2UlRQUHVqNXh0OTcwSlNYQ0RUV0puWjM3RGhGNWlSNDN4YStPY21rQ0F3RUFBYU9CNXpDQjVEQWZCZ05WSFNNRUdEQVdnQlRBZXBob2pZbjdxd1ZrREJGOXFuMWx1TXJNVGpBZEJnTlZIUTRFRmdRVVN0MEdGaHU4OW1pMWR2V0J0cnRpR3JwYWdTOHdEZ1lEVlIwUEFRSC9CQVFEQWdFR01DNEdDQ3NHQVFVRkJ3RUJCQ0l3SURBZUJnZ3JCZ0VGQlFjd0FZWVNhSFIwY0RvdkwyY3VjM2x0WTJRdVkyOXRNQklHQTFVZEV3RUIvd1FJTUFZQkFmOENBUUF3TlFZRFZSMGZCQzR3TERBcW9DaWdKb1lrYUhSMGNEb3ZMMmN1YzNsdFkySXVZMjl0TDJOeWJITXZaM1JuYkc5aVlXd3VZM0pzTUJjR0ExVWRJQVFRTUE0d0RBWUtLd1lCQkFIV2VRSUZBVEFOQmdrcWhraUc5dzBCQVFzRkFBT0NBUUVBQ0U0RXA0Qi9FQlpEWGdLdDEwS0E5TENPMHE2ejZ4RjlrSVFZZmVlUUZmdEpmNmlaQlpHN2VzbldQRGNZQ1pxMng1SWdCelV6Q2VRb1kzSU50T0F5bkllWXhCdDJpV2ZCVUZpd0U2b1RHaHN5cGI3cUVaVk1TR05KNlpsZElEZk0vaXBwVVJhVlM2bmVTWUxBRUhEMExQUHN2Q1FrMEU2c3BkbGVIbTJTd2Flc1NEV0IrZVhrbkdWcHpZZWtRVkEvTGxlbGtWRVNXQTZNQ2FHc2VxUVNwU2Z6bWhDWGZWVURCdmRtV0Y5ZlpPR3JYVzJsT1VoMW1Fd3BXanFOMHl2S25GVUV2L1RtRk5XQXJDYnRGNG1tazJ4Y3BNeTQ4R2FPWk9OOW11SUFzMG5INUFxcTNWdUR4M0NRUms2KzBOdFpsbXd1OVJZMjNuSE1BY0lTd1NIR0ZnPT0iXX0.eyJub25jZSI6InptNUl6SEhzOWtZR050L2RtVndnRlhVZVRjWWVNcS9SbHk5Tlk4TlQ1VUk9IiwidGltZXN0YW1wTXMiOjE0OTk0MTg5NzcwNzUsImFwa1BhY2thZ2VOYW1lIjoiY29tLmNhdGhlcmluZS5zZWN1cml0eXNhbXBsZSIsImFwa0RpZ2VzdFNoYTI1NiI6IlhpblVZLzJ3T1V5WUdlUHZIeXRwVmNQdnB3L3MraVY5RmI3SDJoL3dIWk09IiwiY3RzUHJvZmlsZU1hdGNoIjp0cnVlLCJleHRlbnNpb24iOiJDZUFtK0NHVUF5T0EiLCJhcGtDZXJ0aWZpY2F0ZURpZ2VzdFNoYTI1NiI6WyI5bUxGUzNlSFdPQmNIbEE0TW1PRG1mR3Z6Z2tiZzJZU1Eyei93dzlsQ2Z3PSJdLCJiYXNpY0ludGVncml0eSI6dHJ1ZX0.hXu9WfIR4qZ8yIcLeTfN6OIdsvyUmus2Ym_g3wloJtgTs3-aZpHlndPNtNGxDcrbnzJYs9LXAK4JT5aSuAycMDoFBEoI_iLKRfMiLWN5O6nRDCIHunbKfEdviTK9CkLNb_Yfcg3o-uugmGV-iEmQ_FXtDk0in7oKmdm6yujGbdm-BR69UWhtsX5b_-G4mgXxfW5b8cSp7Y-q-e2r1LtyXJ5Bl76eOk3ncBIOdg6M07y49sofxXRO3VF8QPYSdiaC8zMAWowpatpyW0yBsjXfLFKm8-xOV8vhlf0RCrtH83HYoT1NsU365frAW1y9N5L7NmUVXQuBEpmoRje3E8FCuA.eyJub25jZSI6ImFuWU1GVndXYy9Vcms4U280R252SFVPNXgxeHo1Z3QyaThkRnJzUmI5WEU9IiwidGltZXN0YW1wTXMiOjE0OTkwNjU4NjkyMTMsImFwa1BhY2thZ2VOYW1lIjoiY29tLmNhdGhlcmluZS5zZWN1cml0eXNhbXBsZSIsImFwa0RpZ2VzdFNoYTI1NiI6InFOQTZ0UWo1MnZ5cGlTV0NVTTJaVkVyekpkeVRSMlovTUVjRFhGYU1iVlU9IiwiY3RzUHJvZmlsZU1hdGNoIjp0cnVlLCJleHRlbnNpb24iOiJDYlNsSFZOd21KZ20iLCJhcGtDZXJ0aWZpY2F0ZURpZ2VzdFNoYTI1NiI6WyI5bUxGUzNlSFdPQmNIbEE0TW1PRG1mR3Z6Z2tiZzJZU1Eyei93dzlsQ2Z3PSJdLCJiYXNpY0ludGVncml0eSI6dHJ1ZX0.rrRFPRWVLIk6DYo81UysVcWp40ql8wIhmmIehRDZA9QDknlE5lya4wMDFvvMmpMuN7uS5bKBJdz9kSjdxpmDruoLZNHRDqyXQBX_N3OdTzv4Hta3fCBEVzroz_L3qR_je2IM5KIdQHiSo2ssZOTahLdij3eTCzJx5bJXIcxfleQ2AoXh1ONDyE7qIQFSQhQ_QMYPCMUkzTi1vJaiV5EPCQVrnGFk1XClBtZnVPSudM-61PmcLnQr7OvJYeSvxwclSr7BEhHrhZXxg-Vp5cCAGINAoeop7zfrXdU0SK-9P891JyoGE2XZB3yEcR0l_j8zJpPWfjTGBpKDqIQwa-y32g";

	/**
	 * 
	 */
	private static void testJWS() {
		try {
			JwsHelper jwsHelper = new JwsHelper(attestationJws);
			System.out.println("alg:" + jwsHelper.getAlg());
			AttestationResult result = new AttestationResult(
					jwsHelper.getDecodedPayload());
			System.out.println(result);

			List<X509Certificate> certs = jwsHelper.getX5CCertificates();

			X509Certificate rootCert = CertificatesManager
					.downloadCaIssuersCert(KeySet.GIAG2_URL);

			// Just verify one of the certificates which is belonged to
			// "attest.android.com" in this case.
			boolean isJwsHeaderLegal = false;
			for (X509Certificate cert : certs) {
				boolean isValid = CertificatesManager.validate(cert, rootCert);
				CertificatesManager.printCertificatesInfo(cert);
				if (isValid == true)
					isJwsHeaderLegal = true;
			}

			// Verify the signature of JWS
			boolean isJwsSignatureLegal = jwsHelper
					.verifySignature(Algorithm.ALG_SHA256_WITH_RSA);
			if (isJwsHeaderLegal && isJwsSignatureLegal)
				System.out.println("Android attestion JWS ???????????????");
			else
				System.out.println("Android attestion JWS ???????????????");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void testCryptography() {
		try {
			KeystoreManager.printKeyStoreInfo(KeySet.KEYSTORE_PATH,
					KeySet.KEYSTORE_TYPE, KeySet.KEYSTORE_PW);

			TrackLog log1 = new TrackLog("General a single key");
			Analysis.startTracking(log1);
			String secretKeyString = KeystoreManager.generateKeyString();
			Analysis.endTracking(log1);
			Analysis.printTrack(log1);

			TrackLog log2 = new TrackLog("Decrypt the key");
			Analysis.startTracking(log2);
			KeystoreManager.converStringToKey(secretKeyString);
			Analysis.endTracking(log2);
			Analysis.printTrack(log2);

			final TrackLog log3 = new TrackLog("General a RSA keyPair");
			Analysis.startTracking(log3);
			KeystoreManager.generateRSAKeyPair(new RSACallback() {

				@Override
				public void onResponse(String modulus, String exponent) {
					Analysis.endTracking(log3);
					Analysis.printTrack(log3);

					try {
						TrackLog log4 = new TrackLog("Decrypt the RSA keyPair");
						Analysis.startTracking(log4);
						KeystoreManager.converStringToPublicKey(modulus,
								exponent);
						Analysis.endTracking(log4);
						Analysis.printTrack(log4);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					} catch (InvalidKeySpecException e) {
						e.printStackTrace();
					}
				}

				@Override
				public void onResponse(PrivateKey privateKey) {
				}

				@Override
				public void onResponse(PrivateKey privateKey,
						PublicKey publicKey) {

				}
			});

			TrackLog log5 = new TrackLog("General a keypair from the keystore");
			Analysis.startTracking(log5);
			KeystoreManager.getKeyPairFromKeystore();
			Analysis.endTracking(log5);
			Analysis.printTrack(log5);

			TrackLog log6 = new TrackLog("Encrypt a string from the keyPair");
			Analysis.startTracking(log6);
			byte[] msg = CipherKit.encrypt("????????????");
			Analysis.endTracking(log6);
			Analysis.printTrack(log6);

			TrackLog log7 = new TrackLog("Decrypt a string from the keyPair");
			Analysis.startTracking(log7);
			System.out.println(CipherKit.decrypt(msg));
			Analysis.endTracking(log7);
			Analysis.printTrack(log7);

			final TrackLog log8 = new TrackLog(
					"Encrypt a string from the secretKey key");
			Analysis.startTracking(log8);
			final Key sKey = KeystoreManager.generateKey();
			CipherKit.encryptDES(sKey, "Hi there!", new DESCallback() {

				@Override
				public void onResponse(byte[] iv, byte[] message) {
					Analysis.endTracking(log8);
					Analysis.printTrack(log8);

					try {
						TrackLog log9 = new TrackLog(
								"Decrypt a string from the secretKey key");
						Analysis.startTracking(log9);
						System.out.println(CipherKit.decryptDES(sKey, iv,
								message));
						Analysis.endTracking(log9);
						Analysis.printTrack(log9);
					} catch (InvalidKeyException e) {
						e.printStackTrace();
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					} catch (NoSuchPaddingException e) {
						e.printStackTrace();
					} catch (IllegalBlockSizeException e) {
						e.printStackTrace();
					} catch (BadPaddingException e) {
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					} catch (InvalidAlgorithmParameterException e) {
						e.printStackTrace();
					}

				}
			});
			// verify files
			final TrackLog log10 = new TrackLog("Signing the file ");
			Analysis.startTracking(log10);
			KeystoreManager.generateRSAKeyPair(new RSACallback() {

				@Override
				public void onResponse(String modulus, String exponent) {
				}

				@Override
				public void onResponse(PrivateKey privateKey) {
				}

				@Override
				public void onResponse(PrivateKey privateKey,
						PublicKey publicKey) {

					try {
						byte[] signature = MessageDigestKit.signFiles(
								"assets/metals.jpg",
								Algorithm.ALG_MD5_WITH_RSA, privateKey);
						Analysis.endTracking(log10);
						Analysis.printTrack(log10);

						TrackLog log11 = new TrackLog(
								"verifing the file with signature ");
						Analysis.startTracking(log11);
						boolean islegel = MessageDigestKit.verifyFileSignature(
								signature, Algorithm.ALG_MD5_WITH_RSA,
								"assets/metals.jpg", publicKey);

						System.out
								.println("Signature: "
										+ Base64.getEncoder().encodeToString(
												signature));
						System.out.println("Signature: Is this file legel? "
								+ islegel);
						Analysis.endTracking(log11);
						Analysis.printTrack(log11);

						signature = MessageDigestKit.sign(
								attestationJws.getBytes(),
								Algorithm.ALG_SHA256_WITH_RSA, privateKey);
						islegel = MessageDigestKit.verifySignature(signature,
								Algorithm.ALG_SHA256_WITH_RSA, publicKey,
								attestationJws.getBytes());

						System.out
								.println("Signature: "
										+ Base64.getEncoder().encodeToString(
												signature));
						System.out.println("Signature: Is this file legel? "
								+ islegel);
					} catch (InvalidKeyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SignatureException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

		} catch (IllegalBlockSizeException | NoSuchPaddingException
				| BadPaddingException | InvalidKeyException
				| UnrecoverableKeyException | CertificateException
				| NoSuchAlgorithmException | KeyStoreException | IOException
				| InvalidKeySpecException |

				ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	public static void testRedBlackBST() {
		// 1
		// MyRedBlackBST<Integer> rbTree1 = new MyRedBlackBST<Integer>(20);
		// rbTree1.add(10);
		// rbTree1.add(30);
		// rbTree1.add(15);
		// rbTree1.traverseLevel();

		// 2
		// MyRedBlackBST<Integer> rbTree2 = new MyRedBlackBST<Integer>(40);
		// rbTree2.add(30);
		// rbTree2.traverseLevel();
		// rbTree2.add(50);
		// rbTree2.traverseLevel();
		// rbTree2.add(45);
		// rbTree2.traverseLevel();
		// rbTree2.add(35);
		// rbTree2.traverseLevel();
		// //3
		MyRedBlackBST<Integer> rbTree3 = new MyRedBlackBST<Integer>(40);
		rbTree3.add(30);
		rbTree3.traverseLevel();
		rbTree3.add(50);
		rbTree3.traverseLevel();
		rbTree3.add(45);
		rbTree3.traverseLevel();
		rbTree3.add(25);
		rbTree3.traverseLevel();
	}

	public static void testAVLTree() {
		Random random = new Random();
		int root = 100 + random.nextInt(300);
		final MyAVLTree<Integer> myAVLTree1 = new MyAVLTree<Integer>(root);
		final MyAVLTree<Integer> myAVLTree2 = new MyAVLTree<Integer>(root);
		int SIZE = 1 + random.nextInt(15);
		int[] history = new int[SIZE];
		history[0] = root;
		for (int i = 1; i < SIZE; i++) {
			history[i] = 100 + random.nextInt(300);
			myAVLTree1.add(history[i]);
			myAVLTree2.insertAndBalance(history[i]);
			random = new Random();
		}

		myAVLTree1.isAVLTree(new Callback() {
			@Override
			public void onResponse(boolean result) {
				System.out.println("size:" + myAVLTree1.size());
				myAVLTree1.traverseIn();// ???????????????????????????
				myAVLTree1.traverseLevel();
				System.out.println("Is myAVLTree1 an AVL tree? " + result);

				System.out.println("\n\ninsertAndBalance 9999");
				myAVLTree1.insertAndBalance(9999);
				myAVLTree1.isAVLTree(new Callback() {
					@Override
					public void onResponse(boolean result) {
						myAVLTree1.traverseIn();// ???????????????????????????
						myAVLTree1.traverseLevel();
						System.out.println("Is that still an AVL tree? "
								+ result);
					}
				});
			}
		});

		myAVLTree2.isAVLTree(new Callback() {
			@Override
			public void onResponse(boolean result) {
				System.out.println("size:" + myAVLTree2.size());
				myAVLTree2.traverseIn();// ???????????????????????????
				myAVLTree2.traverseLevel();
				System.out.println("\n\nIs myAVLTree2 an AVL tree? " + result);

				System.out.println("\n\ninsertAndBalance 9999");
				myAVLTree2.insertAndBalance(9999);
				myAVLTree2.isAVLTree(new Callback() {
					@Override
					public void onResponse(boolean result) {
						myAVLTree2.traverseIn();// ???????????????????????????
						myAVLTree2.traverseLevel();
						System.out.println("Is that still an AVL tree? "
								+ result);
					}
				});
			}
		});
	}

	public static void testBST() {
		Random random = new Random();
		char root = (char) ('A' + random.nextInt(26));
		MyBinarySearchTree<Character> mBST = new MyBinarySearchTree<>(root);
		int SIZE = 1 + random.nextInt(15);
		char[] history = new char[SIZE];
		history[0] = root;
		for (int i = 1; i < SIZE; i++) {
			history[i] = (char) ('A' + random.nextInt(26));
			mBST.add(history[i]);
			random = new Random();
		}

		System.out.println("size:" + mBST.size());
		mBST.traverseIn();// ???????????????????????????
		mBST.traverseLevel();

		// removeLCCompletely
		int p = 0 + random.nextInt(history.length);
		Node<Character> n1 = mBST.search(history[p]);
		System.out.println("\n\nremoveLCCompletely:" + n1.getData());
		mBST.removeLCCompletely(n1);
		mBST.removeRCCompletely(mBST.search('%'));
		mBST.traverseIn();// ???????????????????????????
		mBST.traverseLevel();

		// remove
		p = 0 + random.nextInt(history.length);
		Node<Character> n2 = mBST.search(history[p]);
		if (n2 != null) {
			System.out.println("\n\nremove:" + n2.getData());
			mBST.remove(n2.getData());
			mBST.traverseIn();// ???????????????????????????
			mBST.traverseLevel();
		}

		// search
		p = 0 + random.nextInt(history.length);
		System.out.println("\n\nsearch:" + history[p]);
		System.out.println(mBST.search(history[p]));

		// Generate a random BST
		System.out.println("\n\nrandom BST");
		MyBinarySearchTree<Integer> randomBST = (MyBinarySearchTree<Integer>) MyBinarySearchTree
				.random(5, 100, 300);
		System.out.println("Is this BST full? " + randomBST.isFull());
		randomBST.traverseIn();// ???????????????????????????
		randomBST.traverseLevel();
	}

	public static void testBinaryTree() {
		MyBinaryTree<String> mBT = new MyBinaryTree<>("R");
		Node<String> lc = mBT.insertLC(mBT.getRoot(), "A");
		mBT.insertRC(lc, "C");
		Node<String> rc = mBT.insertRC(mBT.getRoot(), "B");
		Node<String> rclc = mBT.insertLC(rc, "D");
		mBT.insertRC(rc, "E");
		mBT.setLC(rclc, "F");

		System.out.println("H:" + mBT.getHeight());
		String[] strings = new String[mBT.size()];
		System.out.println("Level traversal");
		mBT.traverseLevel();
		System.out.println("Pre-order traversal");
		mBT.traversePre();
		System.out.println("In-order traversal");
		mBT.traverseIn();
		System.out.println("Post-order traversal");
		mBT.traversePost();

		System.out.println("\nclone");
		MyBinaryTree<String> clone = (MyBinaryTree<String>) mBT.clone();
		clone.traverseLevel();
		clone.copyInto(Order.PRE_ORDER_RECURSION, strings);
		printArray("copyInto", strings);
		clone.copyInto(Order.IN_ORDER_RECURSION, strings);
		printArray("copyInto", strings);
		clone.copyInto(Order.POST_ORDER_RECURSION, strings);
		printArray("copyInto", strings);
	}

	public static void testDirectedGraph() {
		DirectedGraph<String> dGraph = new DirectedGraph<>();
		// Vertex<String> va = dGraph.addVertex("A");
		// Vertex<String> vc = dGraph.addVertex("C");
		// Vertex<String> vb = dGraph.addVertex(1, "B");
		// Vertex<String> vd = dGraph.addVertex("D");
		// Vertex<String> ve = dGraph.addVertex("E");
		//
		// dGraph.addEdge(va, vb);
		// dGraph.addEdge(vb, vc);
		// dGraph.addEdge(vb, ve);
		// dGraph.addEdge(va, vc);
		// dGraph.addEdge(vc, vd);
		// dGraph.addEdge(vd, ve);
		// dGraph.addEdge(vc, ve);
		// dGraph.removeEdge(vc, va);
		// dGraph.removeEdge(vc, ve);
		Vertex<String> vb = dGraph.addVertex("B");
		Vertex<String> vc = dGraph.addVertex("C");
		Vertex<String> vd = dGraph.addVertex("D");
		// Vertex<String> ve = dGraph.addVertex("E");
		Vertex<String> vf = dGraph.addVertex("F");
		Vertex<String> vg = dGraph.addVertex("G");
		Vertex<String> va = dGraph.addVertex("A");
		dGraph.addEdge(va, vb);
		dGraph.addEdge(va, vc);
		dGraph.addEdge(va, vd);
		dGraph.addEdge(vb, vc);
		dGraph.addEdge(vb, vd);
		dGraph.addEdge(vb, vf);
		dGraph.addEdge(vd, vf);
		dGraph.addEdge(vd, vg);
		dGraph.addEdge(vf, vg);

		dGraph.setVertex(0, "a");
		dGraph.setVertex(1, "b");
		dGraph.setVertex(2, "c");

		System.out.println("size:" + dGraph.size());
		System.out.println(dGraph.nextNbr(va, 2).toString());
		System.out.println(dGraph.getChild(vb, 3).toString());

		// bfs
		System.out.println(dGraph.toString());
		dGraph.printVertexes();
		System.out.println(dGraph.edgeCount());
		dGraph.bfs(vb);
		System.out.println(dGraph.toString());
		dGraph.printVertexes();
		System.out.println(dGraph.edgeCount());
		dGraph.deBfs(vb);
		System.out.println(dGraph.toString());
		dGraph.printVertexes();
		System.out.println(dGraph.edgeCount());

		// bfs???????????????
		System.out.println("bfs vertex:" + dGraph.bfs().toString());

		// dfs
		System.out.println(dGraph.toString());
		dGraph.printVertexes();
		dGraph.dfs(va);
		System.out.println(dGraph.toString());
		dGraph.printVertexes();
	}

	public static void testStack() {
		NumberSystem ns = new NumberSystem();
		System.out.println(ns.convertDecimalToOthers(33646, 35));
		System.out.println(Integer.toString(33646, 35));
		Others other = new Others();
		boolean b = other
				.isBracketsCorrect("(1/Math.sqrt(5)) * (Math.pow(((1 + Math.sqrt(5))/2), n) -Math.pow(((1 - Math.sqrt(5))/2), n))");
		System.out.println(b + "");
		SortableStackPermutation ssp = new SortableStackPermutation();
		Stack<Integer> oriS = new Stack<>();
		oriS.push(1);
		oriS.push(2);
		oriS.push(3);
		oriS.push(4);
		oriS.push(5);
		Stack<Integer> sortedS = new Stack<>();
		sortedS.push(1);
		sortedS.push(2);
		sortedS.push(5);
		sortedS.push(3);
		sortedS.push(4);
		boolean b1 = ssp.isSortableStack(oriS, sortedS);
		System.out.println(b1 + "");
	}

	public static void testLinkedList() {
		MyLinkedList<Integer> list = new MyLinkedList<>();
		list.add(1);
		list.add(1);
		list.add(1);
		list.add(1);
		list.add(3);
		list.add(3);
		list.add(3);
		list.add(5);
		list.add(5);
		list.add(7);
		printList("MyLinkedList", list);
		list.removeDuplicates();
		printList("MyLinkedList", list);
	}

	public static void testArrayList() {
		List<String> a1 = new ArrayList<>();
		List<String> a2 = new ArrayList<>();
		a2 = a1;// ???????????????????????????????????????????????????
		a1.add("hello");
		a1.add("I'm a1!");
		a2.add("I'm a2!");
		System.out.println(a1);
		System.out.println(a2);
		MyArrayList<String> myA1 = new MyArrayList<>();
		List<String> myA2 = new MyArrayList<>();
		myA2 = (List<String>) myA1.clone(); // ???clone()?????????????????????????????????????????????
		myA1.add("hello");
		myA1.add("I'm myA1!");
		myA2.add("I'm myA2!");
		System.out.println(myA1);
		System.out.println(myA2);
	}

	public static void testSearch() {
		Search search = new Search();
		System.out.println("binSearch:"
				+ search.binSearch(input6, 1, 0, input6.length - 1));
		System.out.println("binSearch2:"
				+ search.binSearch2(input6, 1, 0, input6.length - 1));
		System.out.println("fibSearch:"
				+ search.fibSearch(input6, 1, 0, input6.length - 1));
	}

	public static void testSequence() {
		Sequence sequence = new Sequence();
		System.out.println("find " + sequence.find(input1, -10));
		printArray("insert", sequence.insert(input1, 5, 9));
		printArray("remove", sequence.remove(input1, 1, 7));
		printArray("shift", sequence.shift(input2, 2, 5, 7));
		printArray("iterator", sequence.iterator(input1, new Operator() {

			@Override
			public int doSomethine(int input) {
				return ++input;
			}
		}));

		printArray("removeDuplicates", sequence.removeDuplicates(input1));
		printArray("removeDuplicatesAndSort1",
				sequence.removeDuplicatesAndSort1(input6));
		printArray("removeDuplicatesAndSort2",
				sequence.removeDuplicatesAndSort2(input6));

	}

	public static void testTuringMachine() {
		// Increment on Turing Machine
		TuringMachine tMachine = new TuringMachine();
		printArray("TuringMachine",
				tMachine.increase(new int[] { 0, 0, 1, 1, 1, 1 }));

		Sequence sequence = new Sequence();
		// 2 ways to increase the capacity
		sequence.increaseArray();
		sequence.doubleArray();
	}

	public static void testHailstone() {
		Others other = new Others();
		printList("Hailstone", other.getHailstone(42));
	}

	public static void compareStringSorting() {
		Random random = new Random();
		int SIZE = 0 + random.nextInt(15);
		int stringLen = 0 + random.nextInt(10);
		String[] input = new String[SIZE];
		StringBuilder sBuilder = new StringBuilder();

		random = new Random();
		for (int i = 0; i < SIZE; i++) {
			sBuilder.delete(0, stringLen);
			for (int j = 0; j < stringLen; j++) {
				sBuilder.append((char) ('A' + random.nextInt(26)));
			}
			input[i] = sBuilder.toString();
			random = new Random();
		}

		boolean isAscending = (0 + random.nextInt(2)) == 1;
		printArray(String.format("(%s) Raw", (isAscending) ? "Ascending"
				: "Descending"), input);

		BaseSort<String> ms = new MergeSort<>();
		String[] a1 = ms.sort(input, isAscending);
		printArray(String.format("Sorted array (%s)", ms.TAG), a1);

		BaseSort<String> is = new InsertionSort<>();
		String[] a2 = is.sort(input, isAscending);
		// printArray(String.format("Sorted array (%s)", is.TAG), a2);
		if (!Arrays.deepEquals(a1, a2))
			throw new Error(getString(
					String.format("Sorted array (%s)", is.TAG), a2));

		BubbleSort<String> bs = new BubbleSort<>();
		String[] a3 = bs.sort(input, isAscending);
		// printArray(String.format("Sorted array (%s)", bs.TAG), a3);
		if (!Arrays.deepEquals(a1, a3))
			throw new Error(getString(
					String.format("Sorted array (%s)", bs.TAG), a3));

		String[] a4 = bs.sort2(input, isAscending);
		// printArray(String.format("Sorted array (%s)", bs.TAG), a4);
		if (!Arrays.deepEquals(a1, a4))
			throw new Error(getString(
					String.format("Sorted array (%s)", bs.TAG), a4));

		String[] a5 = bs.sort3(input, isAscending);
		// printArray(String.format("Sorted array (%s)", bs.TAG), a5);
		if (!Arrays.deepEquals(a1, a5))
			throw new Error(getString(
					String.format("Sorted array (%s)", bs.TAG), a5));

		BaseSort<String> ss = new SelectionSort<>();
		String[] a6 = ss.sort(input, isAscending);
		// printArray(String.format("Sorted array (%s)", ss.TAG), a6);
		if (!Arrays.deepEquals(a1, a6))
			throw new Error(getString(
					String.format("Sorted array (%s)", ss.TAG), a6));

		HeapSort<String> hs = new HeapSort<>();
		String[] a7 = hs.sort(input, isAscending);
		// printArray(String.format("Sorted array (%s)", hs.TAG), a7);
		if (!Arrays.deepEquals(a1, a7))
			throw new Error(getString(
					String.format("Sorted array (%s)", hs.TAG), a7));

		String[] a8 = hs.sort2(input, isAscending);
		// printArray(String.format("Sorted array (%s)", hs.TAG), a8);
		if (!Arrays.deepEquals(a1, a8))
			throw new Error(getString(
					String.format("Sorted array (%s)", hs.TAG), a8));
	}

	public static void compareIntSorting() {
		Random random = new Random();
		int SIZE = 0 + random.nextInt(15);
		int MIN_INT = 0 + random.nextInt(10);
		int MAX_INT = 10000;
		Integer[] input = new Integer[SIZE];

		random = new Random();
		for (int i = 0; i < SIZE; i++) {
			input[i] = MIN_INT + random.nextInt(MAX_INT);
			random = new Random();
		}

		// TODO
//		Integer[] input = { 6, 3, 8, 1, 5, 9, 8, 4, 5, 7, 2 };
//		boolean isAscending = true;

		 boolean isAscending = (0 + random.nextInt(2)) == 1;
		printArray(String.format("(%s) Raw", (isAscending) ? "Ascending"
				: "Descending"), input);

		BaseSort<Integer> ms = new MergeSort<>();
		Integer[] a1 = ms.sort(input, isAscending);
		printArray(String.format("Sorted array (%s)", ms.TAG), a1);

		BaseSort<Integer> is = new InsertionSort<>();
		Integer[] a2 = is.sort(input, isAscending);
		printArray(String.format("Sorted array (%s)", is.TAG), a2);
		if (!Arrays.deepEquals(a1, a2))
			throw new Error(getString(
					String.format("Sorted array (%s)", is.TAG), a2));

		BubbleSort<Integer> bs = new BubbleSort<>();
		Integer[] a3 = bs.sort(input, isAscending);
		printArray(String.format("Sorted array (%s)", bs.TAG), a3);
		if (!Arrays.deepEquals(a1, a3))
			throw new Error(getString(
					String.format("Sorted array (%s)", bs.TAG), a3));

		Integer[] a4 = bs.sort2(input, isAscending);
		printArray(String.format("Sorted array (%s)", bs.TAG), a4);
		if (!Arrays.deepEquals(a1, a4))
			throw new Error(getString(
					String.format("Sorted array (%s)", bs.TAG), a4));

		Integer[] a5 = bs.sort3(input, isAscending);
		printArray(String.format("Sorted array (%s)", bs.TAG), a5);
		if (!Arrays.deepEquals(a1, a5))
			throw new Error(getString(
					String.format("Sorted array (%s)", bs.TAG), a5));

		BaseSort<Integer> ss = new SelectionSort<>();
		Integer[] a6 = ss.sort(input, isAscending);
		printArray(String.format("Sorted array (%s)", ss.TAG), a6);
		if (!Arrays.deepEquals(a1, a6))
			throw new Error(getString(
					String.format("Sorted array (%s)", ss.TAG), a6));

		HeapSort<Integer> hs = new HeapSort<>();
		// TODO
		// Integer[] a7 = hs.sort(input, isAscending);
		// printArray(String.format("Sorted array (%s)", hs.TAG), a7);
		// if (!Arrays.deepEquals(a1, a7))
		// throw new Error(getString(
		// String.format("Sorted array (%s)", hs.TAG), a7));

		// Integer[] a8 = hs.sort2(input, isAscending);
		// printArray(String.format("Sorted array (%s)", hs.TAG), a8);
		// if (!Arrays.deepEquals(a1, a8))
		// throw new Error(getString(
		// String.format("Sorted array (%s)", hs.TAG), a8));

		QuickSort<Integer> qs = new QuickSort<Integer>();
		Integer[] a9 = qs.sort(input, isAscending);
		printArray(String.format("Sorted array (%s1)", qs.TAG), a9);
		if (!Arrays.deepEquals(a1, a9))
			throw new Error(getString(
					String.format("Sorted array (%s1)", qs.TAG), a9));

		Integer[] a10 = qs.sort2(input, isAscending);
		printArray(String.format("Sorted array (%s2)", qs.TAG), a10);
		if (!Arrays.deepEquals(a1, a10))
			throw new Error(getString(
					String.format("Sorted array (%s2)", qs.TAG), a10));
	}

	public static void printIterator(Iterator<?> it) {
		Iterator<?> iterator = it;
		System.out.print("[");
		StringBuilder sBuilder = new StringBuilder();
		while (iterator.hasNext())
			sBuilder.append(iterator.next() + ", ");
		System.out.println(sBuilder.substring(0, sBuilder.length() - 2) + "]");
	}

	public synchronized static void printArray(String title, Object[] array) {
		System.out
				.println("--------------------------------------------------------");
		if (array == null)
			System.out.println("null");
		else {
			System.out.print(title + " -> [");
			for (int i = 0; i < array.length; i++) {
				System.out.print(array[i]);
				if (i != array.length - 1)
					System.out.print(", ");
				else
					System.out.println("]");
			}
		}
		System.out
				.println("--------------------------------------------------------");
	}

	public static void printArray(String title, int[] array) {
		System.out
				.println("--------------------------------------------------------");
		if (array == null)
			System.out.println("null");
		else {
			System.out.print(title + " -> [");
			for (int i = 0; i < array.length; i++) {
				System.out.print(array[i]);
				if (i != array.length - 1)
					System.out.print(", ");
				else
					System.out.println("]");
			}
		}
		System.out
				.println("--------------------------------------------------------");
	}

	public static void printList(String title, List<?> list) {
		System.out
				.println("--------------------------------------------------------");
		if (list == null)
			System.out.println("null");
		else {
			System.out.print(title + " -> [");
			for (int i = 0; i < list.size(); i++) {
				System.out.print(list.get(i));
				if (i != list.size() - 1)
					System.out.print(", ");
				else
					System.out.println("]");
			}
		}
		System.out
				.println("--------------------------------------------------------");
	}

	public static String getString(String title, Object[] array) {
		if (array == null)
			return "null";
		else {
			StringBuilder sb = new StringBuilder();
			sb.append(title + " -> [");
			for (int i = 0; i < array.length; i++) {
				sb.append(array[i]);
				if (i != array.length - 1)
					sb.append(", ");
				else
					sb.append("]");
			}
			return sb.toString();
		}
	}
}