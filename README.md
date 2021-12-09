# Huffman-Encoding
Data Structures CSCI-UA-102
========
Section 001 Korth
---------
Uses Trees and BinaryHeaps to create a Huffman Encoding of a message as a .txt file. Uses code for a BinaryHeap implementation from Weiss.
<br>
HuffmanNode - a class that creates nodes for the HuffmanTree that contain characters and their frequencies
<br>
HuffmanTree - uses HuffmanNodes to build a tree from a BinaryHeap, and can also convert a given legend into a Huffman Tree, where characters with higher frequencies are represented with fewer bits
<br>
HuffmanConverter - encodes and decodes a .txt file based on Huffman encoding.
