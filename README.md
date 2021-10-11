# Algorithms

## Table of contents

- [Algorithms](#algorithms)
  - [Table of contents](#table-of-contents)
  - [Dynamic connectivity](#dynamic-connectivity)
    - [Quick Find - An eager approach](#quick-find---an-eager-approach)
    - [Quick Union - A lazy approach](#quick-union---a-lazy-approach)
    - [Improving quick find and quick union](#improving-quick-find-and-quick-union)
    - [Percolation](#percolation)
  - [References](#references)

## Dynamic connectivity

Given a set of N objects. A union-find class includes:

-   `UF(int N)`: The constructor of the UF class that initialises a union-find data structure with `N` objects.
-   `void union(a, b)`: A union command connects two objects.
-   `boolean find/connect(a, b)`: Are a and b in the same component?

### Quick Find - An eager approach

![QF]

In quick union, we need an array to keep nodes' locations. For each node `i`, the array `id[i]` keeps where the node `i` points to. When node p unions to q, we must loop the array and replace all the values that equal to `id[p]` with `id[q]`.

Example 1

```
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9] -> union(4, 3)
[0, 1, 2, 3, 3, 5, 6, 7, 8, 9] -> union(3, 8)
[0, 1, 2, 8, 8, 5, 6, 7, 8, 9] -> union(6, 5)
[0, 1, 2, 8, 8, 5, 5, 7, 8, 9] -> union(9, 4)
[0, 1, 2, 8, 8, 5, 5, 7, 8, 8] -> union(2, 1)
[0, 1, 1, 8, 8, 5, 5, 7, 8, 8] -> union(5, 0)
[0, 1, 1, 8, 8, 0, 0, 7, 8, 8] -> union(7, 2)
[0, 1, 1, 8, 8, 0, 0, 1, 8, 8] -> union(6, 1)
[1, 1, 1, 8, 8, 1, 1, 1, 8, 8] -> connected(0, 2)
true // array[0] == array[2]
```

### Quick Union - A lazy approach

![QU1]
![QU1]

Quick-find is too slow for huge problems. An alternative is called quick union. In quick union, we need an array to keep nodes' locations. For each node `i`, the array `id[i]` keeps where the node `i` points to. E.g., `id[0] = 0` means the node `0` has no parents. `id[3] = 9` means the node `3` is pointing to the node `9`. When node p points to node q, the root of p points to the root of q.

E.g.,

```
   i    0  1  2  3  4  5  6  7  8  9
-------------------------------------
id[i]   0  9  6  5  4  2  6  1  0  5
```

The roots of 3 and 7 are both 6.

### Improving quick find and quick union

|  Algorithm  | Initialise | Union | Find  |
| :---------: | :--------: | :---: | :---: |
| Quick Find  |     N      |   N   |   1   |
| Quick Union |     N      |   N   |   N   |
| Weighted QU |     N      | log N | log N |

If the tree becomes flatter, the cost of find in the quick union will be less. Weighted quick union is an improvement of the quick union. Instead of joining the root of p to the root of q, it compares the size of trees where p and q are and merge the root with a smaller tree to the other root.

### Percolation

![percolation]

The Union-Find is a model for many physical systems, specifically, systems percolates iff top and bottom are connected by open sites.

[programming assignment: percolation]

## References

-   [Algorithms, Part 1]

[uf]: ./res/UF.png
[qu1]: ./res/QU1.png
[qu2]: ./res/QU2.png
[percolation]: ./res/percolation.png
[algorithms, part 1]: https://www.coursera.org/learn/algorithms-part1/home/welcome
[programming assignment: percolation]: https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php
