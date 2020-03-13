package ontologyevolutionvisualizer.visualizer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import ontologyevolutionvisualizer.OntoConstants;
import ontologyevolutionvisualizer.virtuososervice.VirtuosoService;

/**
 *
 * @author Antonis
 */
public class GraphView extends JFrame {
    //private static String changeOperatorChoice = "0";
    //private static String propertyChoice = "";
    
    private VirtuosoService vs = new VirtuosoService();
    private SimpleGraph graph = new SimpleGraph();
    
    private ArrayList<ArrayList<ArrayList<String>>> results = new ArrayList<>(); // triple ArrayList
    private JComboBox changeOperatorsList;
    private JTextField txURI;

    public GraphView() {
        init();
    }

    private void setGraph(ArrayList<ArrayList<ArrayList<String>>> results){
        graph.initGraph(results);
        add(graph, BorderLayout.CENTER);
        repaint();
    }
    
    private void init() {
        setTitle("OntologyEvolutionVisualizer - 4th Team (Providakis ΜΠ148, Trivizakis ΜΠ143)");
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout lay = new BorderLayout();
        JPanel controls = new JPanel();
        controls.setLayout(new FlowLayout());
        setLayout(lay);
        
        changeOperatorsList = new JComboBox(OntoConstants.changeOperators.toArray());
        controls.add(changeOperatorsList);
        
        txURI = new JTextField();
        txURI.setColumns(30);
        controls.add(txURI);
        
        JButton btnFind = new JButton("Find");
        getClick(btnFind);
        controls.add(btnFind);
        
        add(controls, BorderLayout.SOUTH);
        
        this.setBounds(20, 20, 1100, 750);
        setVisible(true);
    }
    
    private void getClick(JButton btn){
        btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            whenClick();
			}
                    });
    }
    
    private void whenClick(){
        graph.removeAll();
        vs.setChoices(""+changeOperatorsList.getSelectedIndex(), txURI.getText());
        //triple ArrayList:
        results = vs.justRun();
         
        setGraph(results);
    }                
}