## Solution Description

Solution consists of four classes: **TreeDifferences**, **GumTreeDataFetcher**, **AstTreeCellRenderer** and **MappedTreesListener**

To build this app it is needed to make a setup of GumTree and to add it's libraries to this project as a dependency.

### TreeDifferences
This is a top level component which initializes **GumTreeDataFetcher** and form which is shown to the user of the application. It's constructor is pretty simple in this regard as its only purpose is component initialization and form construction.

### GumTreeDataFetcher
This is the main working class in application. It is designed to obtain all the data on AST structure of input and to get all the info on mappings between them. For this purpose it uses GumTree library code.

#### Constructor
Constructor takes two file names as it's arguments and requestes their syntax tree representations from GumTree **Generator**. **ITree** is a node representation for generated trees. After that the mappings are built via means of the **Matcher**-class, mappings connect some nodes of one tree to nodes of the other. Then all the data is passed to **ActionGenerator** to obtain list of **Action** elements. Each of them describes some modification of node needed to obtain one list from the other. Based on these actions a number of "action" sets for **ITree** components is built. After this *buildNode* method is called for both source and destination trees (Top nodes of these trees to be more precise).

#### buildNode
Based on tree passed to this method it recursively creates tree representation with **MutableTreeNode** elements which can later be used to initialise JTree component. **ITree** elements are passed to these nodes constructors, which makes it possible to obtain such element from the corresponding node. To do the opposite the hashMap is filled in the very same method.

#### Other methods
There are also a lot of other methods which are made for obtaining different info on **ITree** which can be useful for other components.

### AstTreeCellRenderer
This custom renderer is needed to display which nodes are modified. It communicates with **GumTreeDataFetcher** to get data on the **ITree** element which it obtains from **DefaultMutableTreeNode** instance. Based on what set does this element belong the node is colored into different colors. Red for deleted, purple for updated, blue for moved and green for inserted.

### MappedTreesListener
This component is needed so that whenever an element on one tree is selected, the other trees element it's mapped to is also selected. It gives a clear idea on these very mapping between trees nodes.
