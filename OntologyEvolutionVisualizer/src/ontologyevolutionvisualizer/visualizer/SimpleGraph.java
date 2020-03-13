package ontologyevolutionvisualizer.visualizer;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import ontologyevolutionvisualizer.OntoConstants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.activity.Activity;
import prefuse.controls.DragControl;
import prefuse.controls.PanControl;
import prefuse.controls.WheelZoomControl;
import prefuse.data.Graph;
import prefuse.data.Node;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.EdgeRenderer;
import prefuse.render.LabelRenderer;
import prefuse.util.ColorLib;
import prefuse.util.force.SpringForce;
import prefuse.visual.VisualItem;

/**
 *
 * @author Antonis
 */
public class SimpleGraph extends Display {
    
    private Graph graph;
    private Boolean previousSecondaryNode = false;
    public SimpleGraph(){

    }

    private void setUpData(ArrayList<ArrayList<ArrayList<String>>> results) {
        graph = new Graph(true); // directed graph
     
        // first define a column
        graph.addColumn(OntoConstants.LABEL, String.class);
        //graph.addColumn(SIZE, int.class);       
        if(results.isEmpty()){
            addNode("Nothing Found!");
        }
        ArrayList<Node> incoming = new ArrayList<>();
        for(int i=0; i<results.size(); i++){ //potential multiple chOp per file
            //if(i==results.size()-1)
            //    System.out.println("-L1---HOW MANE CHANGES? "+results.size()+"----"+i);
            for(int j=0; j<results.get(i).size(); j++){ //potential nodes per query
                //System.out.println("-L2---HOW MANE CHANGES? "+results.get(i).size()+"----"+j);
                //for(int h=0; h<results.get(i).get(j).size(); h++){  // String of node
                    //System.out.println(i+"-"+j+"-"+h+": "+results.get(i).get(j).get(h));
                    if(incoming.isEmpty()){
                        System.out.println("_______First______");
                        incoming.add(graphEngineer(results.get(i).get(j),null));
                    }
                    else{
                        System.out.println("_______"+i+"______");
                        incoming.add(graphEngineer(results.get(i).get(j),incoming.get(incoming.size()-1)));
                    }
                //}
            }
        }
        results.clear();
        incoming.clear();   
    }

    private void setUpVisualization() {
        // Create the Visualization object.
        setVisualization(new Visualization());
        // Now we add our previously created Graph object
        // to the visualization.
        // The graph gets a textual label so that we can refe r
        // to it later on.
        m_vis.addGraph(OntoConstants.GRAPH, graph);
        m_vis.setInteractive(OntoConstants.EDGES, null, false);
    }

    private void setUpRenderers() {
        DefaultRendererFactory rendererFactory = new DefaultRendererFactory();
        LabelRenderer laberRenderer = new LabelRenderer(OntoConstants.LABEL);
        laberRenderer.setRoundedCorner(8, 8); // round the corners
        rendererFactory.setDefaultRenderer(laberRenderer);
        EdgeRenderer edgeRenderer = new EdgeRenderer(prefuse.Constants.EDGE_TYPE_LINE, prefuse.Constants.EDGE_ARROW_FORWARD);
        edgeRenderer.setArrowHeadSize(7, 50);
        rendererFactory.setDefaultEdgeRenderer(edgeRenderer);
        m_vis.setRendererFactory(rendererFactory);
    }

    private void setUpActions() {
        ColorAction nodeText = new ColorAction(OntoConstants.NODES, VisualItem.TEXTCOLOR);
        nodeText.setDefaultColor(ColorLib.color(Color.black));

        ColorAction nStroke = new ColorAction(OntoConstants.NODES, VisualItem.STROKECOLOR);
        nStroke.setDefaultColor(ColorLib.gray(100));
        nStroke.add("_hover", ColorLib.gray(50));

        ColorAction nFill = new ColorAction(OntoConstants.NODES, VisualItem.FILLCOLOR);
        nFill.setDefaultColor(ColorLib.color(Color.yellow));
        nFill.add("_hover", ColorLib.gray(200));

        ColorAction eStroke = new ColorAction(OntoConstants.EDGES, VisualItem.STROKECOLOR);
        eStroke.setDefaultColor(ColorLib.color(Color.RED));

        ColorAction eFill = new ColorAction(OntoConstants.EDGES, VisualItem.FILLCOLOR);
        eFill.setDefaultColor(ColorLib.color(Color.red));

        // bundle the color actions
        ActionList colors = new ActionList();
        colors.add(nodeText);
        colors.add(nStroke);
        colors.add(nFill);
        colors.add(eStroke);
        colors.add(eFill);
        m_vis.putAction("colors", colors);

        // now create the main layout routine
        ActionList layout = new ActionList(Activity.INFINITY);
        ForceDirectedLayout fdl = new ForceDirectedLayout(OntoConstants.GRAPH, true);
        fdl.getForceSimulator().getForces()[2].setParameter(SpringForce.SPRING_LENGTH, 50); // set spring length (edge length)
        layout.add(fdl);
        layout.add(new RepaintAction());
        m_vis.putAction("layout", layout);
    }

    private void setUpDisplay() {
        setSize(1100, 700);
        pan(250, 250);
        zoom(new Point(250, 250), 1.5);
        setHighQuality(true);
        addControlListener(new DragControl());
        addControlListener(new PanControl());
        addControlListener(new WheelZoomControl());
    }
    
    
    public void initGraph(ArrayList<ArrayList<ArrayList<String>>> results) {
        setUpData(results);
        setUpVisualization();
        setUpRenderers();
        setUpActions();
        setUpDisplay();

        // set things running
        m_vis.run("colors");
        m_vis.run("layout");
    }
    private Node graphEngineer(ArrayList<String> result, Node incoming){
            String changeOperator = result.get(0);
            
            if(OntoConstants.changeOperators.get(0).equals(changeOperator)){ //pull_down_class
                return pullDown(result, incoming);
            }
            if(OntoConstants.changeOperators.get(1).equals(changeOperator)){ //change_to_datatype_property
                return changeDatatype(result, incoming);
            }        
            if(OntoConstants.changeOperators.get(2).equals(changeOperator)){ //generalize_range
                return generalizeRange(result, incoming);
            }        
            if(OntoConstants.changeOperators.get(3).equals(changeOperator)){ //generalize_domain
                return generalizeDomain(result, incoming);
            }        
            if(OntoConstants.changeOperators.get(4).equals(changeOperator)){ //delete_class
                return deleteClass(result, incoming);
            }
            if(OntoConstants.changeOperators.get(5).equals(changeOperator)
                    ||OntoConstants.changeOperators.get(8).equals(changeOperator)){//add_super
                return addSuper(result, incoming);
            }        
            if(OntoConstants.changeOperators.get(6).equals(changeOperator) 
                    || OntoConstants.changeOperators.get(7).equals(changeOperator)){//delete_super
                return deleteSuper(result, incoming);
            }        
            if(OntoConstants.changeOperators.get(9).equals(changeOperator)){ //add_property
                return addProperty(result, incoming);
            }        
            if(OntoConstants.changeOperators.get(10).equals(changeOperator)){ //delete_property
                return deleteProperty(result, incoming);
            }        
            if(OntoConstants.changeOperators.get(11).equals(changeOperator)){ //add_class
                return addClass(result, incoming);
            }
            if(OntoConstants.changeOperators.get(12).equals(changeOperator)
                    || OntoConstants.changeOperators.get(13).equals(changeOperator)){ //rename
                return rename(result, incoming);
            }
        return (Node) null; //never
    }

//    private Node addNode(String label, int size) {
    private Node addNode(String label) {
        Node node = graph.addNode();
        node.setString(OntoConstants.LABEL, label);
//        node.setInt(SIZE, size);
        return node;
    }
    private Node rename(ArrayList<String> result, Node incoming){
        Node rename;
        Node o;
        Node outcoming;        
        if(incoming == null){
            rename = addNode(result.get(3)+"->"+result.get(0)+"->"+result.get(4));
            o = addNode(result.get(1));
            outcoming = addNode(result.get(2));
            
            graph.addEdge(o, rename);
            graph.addEdge(rename, outcoming);
        }
        else{
            rename = addNode(result.get(3)+"->"+result.get(0)+"->"+result.get(4));
            outcoming = addNode(result.get(2));
            
            graph.addEdge(incoming, rename);
            graph.addEdge(rename, outcoming);
        }        
        return outcoming;
    }
    
    private Node deleteSuper(ArrayList<String> result, Node incoming){
        Node deleteSuper;
        Node o;
        Node oo;
        Node outcoming;
        if(incoming == null){
            deleteSuper = addNode(result.get(3)+"->"+result.get(0)+"->"+result.get(4));
            o = addNode(result.get(1));
            outcoming = addNode(result.get(1));
            oo = addNode(result.get(2));
            
            graph.addEdge(o, oo);
            graph.addEdge(oo, deleteSuper);
            graph.addEdge(deleteSuper, outcoming);
        }
        else{
            deleteSuper = addNode(result.get(3)+"->"+result.get(0)+"->"+result.get(4));
            outcoming = addNode(result.get(1));
            
            if(!previousSecondaryNode){
                oo = addNode(result.get(2));
                
                graph.addEdge(incoming, oo);
                graph.addEdge(oo, deleteSuper);
                graph.addEdge(deleteSuper, outcoming);
            }
            else{                
                graph.addEdge(incoming, incoming.getParent());
                graph.addEdge(incoming.getParent(), deleteSuper);
                graph.addEdge(deleteSuper, outcoming);
            }
        }
        return outcoming;
    }
    
    private Node addSuper(ArrayList<String> result, Node incoming){
        Node addSuper;
        Node o;
        Node oo;
        Node outcoming;
        if(incoming == null){
            addSuper = addNode(result.get(3)+"->"+result.get(0)+"->"+result.get(4));
            o = addNode(result.get(1));
            outcoming = addNode(result.get(1));
            oo = addNode(result.get(2));
            
            graph.addEdge(o, addSuper);
            graph.addEdge(addSuper, oo);
            graph.addEdge(oo, outcoming);
        }
        else{
            addSuper = addNode(result.get(3)+"->"+result.get(0)+"->"+result.get(4));
            outcoming = addNode(result.get(1));
            oo = addNode(result.get(2));
            previousSecondaryNode=true;
            
            graph.addEdge(incoming, addSuper);
            graph.addEdge(addSuper, oo);
            graph.addEdge(oo, outcoming);
        }
        return outcoming;
    }
//    private Node TEMPLATE(ArrayList<String> result, Node incoming){
//        Node changeOperator;
//        Node o;
//        Node oo;
//        Node ooo;    
//        Node outcoming;
//        if(incoming == null){
//            
//        }
//        else{
//            
//        }
//        return outcoming;
//    }
    private Node addClass(ArrayList<String> result, Node incoming) {
        Node changeOperator;
        Node oo;
        Node oo2;
        Node outcoming;
        if(incoming == null){
            changeOperator = addNode(result.get(3)+"->"+result.get(0)+"->"+result.get(4));
            outcoming = addNode(result.get(1));
            oo = addNode(result.get(2));
            oo2 = addNode(result.get(2));
            
            graph.addEdge(oo, changeOperator);
            graph.addEdge(changeOperator, oo2);
            graph.addEdge(oo2, outcoming);            
        }
        else{
            changeOperator = addNode(result.get(3)+"->"+result.get(0)+"->"+result.get(4));
            outcoming = addNode(result.get(1));
            oo2 = addNode(result.get(2));
            
            graph.addEdge(incoming, changeOperator);
            graph.addEdge(changeOperator, oo2);
            graph.addEdge(oo2, outcoming); 
        }
        return outcoming;
    }

    private Node deleteProperty(ArrayList<String> result, Node incoming) {
        Node changeOperator;
        Node o;
        if(incoming == null){
             changeOperator = addNode(result.get(2)+"->"+result.get(0)+"->"+result.get(3));
             o = addNode(result.get(1));
             
             graph.addEdge(o, changeOperator);
        }
        else{
             changeOperator = addNode(result.get(3)+"->"+result.get(0)+"->"+result.get(4));
             
             graph.addEdge(incoming, changeOperator);             
        }
        return null;
    }

    private Node addProperty(ArrayList<String> result, Node incoming) {
        Node changeOperator;
        Node o;
        Node outcoming;
        if(incoming == null){
             changeOperator = addNode(result.get(4)+"->"+result.get(0)+"->"+result.get(5));
             outcoming = addNode(result.get(1));
             o = addNode("domain: "+result.get(2)+" range: "+result.get(3));
             
             graph.addEdge(changeOperator, outcoming);
             graph.addEdge(outcoming, o);
        }
        else{
             changeOperator = addNode(result.get(4)+"->"+result.get(0)+"->"+result.get(5));
             outcoming = incoming;
             o = addNode("domain: "+result.get(2)+" range: "+result.get(3));
             
             graph.addEdge(changeOperator, outcoming);
             graph.addEdge(outcoming, o);
        }
        return outcoming;
    }

    private Node deleteClass(ArrayList<String> result, Node incoming) {
        Node changeOperator;
        Node o;
        Node oo;
        Node ooo;    
        Node outcoming;
        if(incoming == null){
             changeOperator = addNode(result.get(3)+"->"+result.get(0)+"->"+result.get(4));
             o = addNode(result.get(1));
             oo = addNode(result.get(2));
             ooo = addNode(result.get(2));
             
             graph.addEdge(o , oo);
             graph.addEdge(oo, changeOperator);
             graph.addEdge(changeOperator, ooo);
        }
        else{
             changeOperator = addNode(result.get(3)+"->"+result.get(0)+"->"+result.get(4));
             oo = addNode(result.get(2));
             ooo = addNode(result.get(2));
             
             graph.addEdge(incoming , oo);
             graph.addEdge(oo, changeOperator);
             graph.addEdge(changeOperator, ooo);
        }
        return null;
    }

    private Node generalizeDomain(ArrayList<String> result, Node incoming) {
        Node changeOperator;
        Node o;
        Node oo;
        Node ooo;    
        Node outcoming;
        if(incoming == null){
             changeOperator = addNode(result.get(4)+"->"+result.get(0)+"->"+result.get(5));
             outcoming = addNode(result.get(1));
             o = addNode(result.get(1));
             oo = addNode("domain: "+result.get(2));
             ooo = addNode("domain: "+result.get(3));
             
             graph.addEdge(oo, o);
             graph.addEdge(o, changeOperator);
             graph.addEdge(changeOperator, outcoming);
             graph.addEdge(outcoming, ooo);
        }
        else{
             changeOperator = addNode(result.get(4)+"->"+result.get(0)+"->"+result.get(5));
             outcoming = addNode(result.get(1));
             oo = addNode("domain: "+result.get(2));
             ooo = addNode("domain: "+result.get(3));
             
             graph.addEdge(oo, incoming);
             graph.addEdge(incoming, changeOperator);
             graph.addEdge(changeOperator, outcoming);
             graph.addEdge(outcoming, ooo);
        }
        return outcoming;
    }

    private Node generalizeRange(ArrayList<String> result, Node incoming) {
        Node changeOperator;
        Node o;
        Node oo;
        Node ooo;    
        Node outcoming;
        if(incoming == null){
             changeOperator = addNode(result.get(4)+"->"+result.get(0)+"->"+result.get(5));
             outcoming = addNode(result.get(1));
             o = addNode(result.get(1));
             oo = addNode("range: "+result.get(2));
             ooo = addNode("range: "+result.get(3));
             
             graph.addEdge(oo, o);
             graph.addEdge(o, changeOperator);
             graph.addEdge(changeOperator, outcoming);
             graph.addEdge(outcoming, ooo);
        }
        else{
             changeOperator = addNode(result.get(4)+"->"+result.get(0)+"->"+result.get(5));
             outcoming = addNode(result.get(1));
             oo = addNode("range: "+result.get(2));
             ooo = addNode("range: "+result.get(3));
             
             graph.addEdge(oo, incoming);
             graph.addEdge(incoming, changeOperator);
             graph.addEdge(changeOperator, outcoming);
             graph.addEdge(outcoming, ooo);
        }
        return outcoming;
    }

    private Node changeDatatype(ArrayList<String> result, Node incoming) {
        Node changeOperator;
        Node o;
        Node oo;
        Node ooo;    
        Node outcoming;
        if(incoming == null){
             changeOperator = addNode(result.get(0));
             outcoming = addNode(result.get(1));
             oo = addNode(result.get(4)+": "+result.get(2));
             ooo = addNode(result.get(5)+": "+result.get(3));
             
             graph.addEdge(changeOperator, outcoming);
             graph.addEdge(outcoming, oo);
             graph.addEdge(oo, ooo);
        }
        else{
             changeOperator = addNode(result.get(0));
             outcoming = incoming;
             oo = addNode(result.get(4)+": "+result.get(2));
             ooo = addNode(result.get(5)+": "+result.get(3));
             
             graph.addEdge(changeOperator, outcoming);
             graph.addEdge(outcoming, oo);
             graph.addEdge(oo, ooo);
        }
        return outcoming;
    }

    private Node pullDown(ArrayList<String> result, Node incoming) {
        Node changeOperator;
        Node o;
        Node oo;
        Node ooo;    
        Node outcoming;
        if(incoming == null){
             changeOperator = addNode(result.get(4)+"->"+result.get(0)+"->"+result.get(5));
             outcoming = addNode(result.get(1));
             o = addNode(result.get(1));
             oo = addNode(result.get(2));
             ooo = addNode(result.get(3));
             
             graph.addEdge(oo, o);
             graph.addEdge(o, changeOperator);
             graph.addEdge(changeOperator, outcoming);
             graph.addEdge(outcoming, ooo);
        }
        else{
             changeOperator = addNode(result.get(4)+"->"+result.get(0)+"->"+result.get(5));
             outcoming = addNode(result.get(1));
             oo = addNode(result.get(2));
             ooo = addNode(result.get(3));
             
             graph.addEdge(oo, incoming);
             graph.addEdge(incoming, changeOperator);
             graph.addEdge(changeOperator, outcoming);
             graph.addEdge(outcoming, ooo);
        }
        return outcoming;
    }
}