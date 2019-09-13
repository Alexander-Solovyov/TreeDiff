package com.alexander.solovyov;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

final public class TreeDifferences extends JFrame {

    private TreeDifferences(final String src, final String dest) {
        super("AST Differences");
        GumTreeDataFetcher fetcher;
        try {
            fetcher = new GumTreeDataFetcher(src, dest);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to init GumTree!");
            return;
        }
        setBounds(100, 100, 800, 600);

        final JTree leftPane = new JTree(fetcher.getSourceNode());
        leftPane.setCellRenderer(new AstTreeCellRenderer(true, fetcher));
        leftPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        final JTree rightPane = new JTree(fetcher.getDestNode());
        rightPane.setCellRenderer(new AstTreeCellRenderer(false, fetcher));
        rightPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        new MappedTreesListener(leftPane, rightPane, fetcher);

        final JSplitPane pane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(leftPane), new JScrollPane(rightPane));
        pane.setBorder(BorderFactory.createEmptyBorder(3, 4, 3, 4));
        add(pane);
        pane.setDividerLocation(0.5f);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            JOptionPane.showMessageDialog(null, "Expected names of two files for comparison");
            return;
        }
        TreeDifferences app = new TreeDifferences(args[0], args[1]);
        app.setVisible(true);
    }
}
