package vacuumCleaner.exploration;

public class NodeAStar extends Node implements Comparable<NodeAStar>{

//Attributes
	private Integer nodeValue = null;

//Constructor
	public NodeAStar() {
		
	}
	
//Getter & setter
	public int getNodeValue() {
		return nodeValue;
	}

	public void setNodeValue(Integer nodeValue) {
		this.nodeValue = nodeValue;
	}
	
	@Override
	public int compareTo(NodeAStar nodeToCompare) {
		return nodeValue - nodeToCompare.getNodeValue();
	}
}
