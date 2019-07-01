package com.marcomorais.datastructures;

public class Vertex {
	String label;
	int weight;

    Vertex(String label) {
        this(label, 0);
    }
    
    Vertex(String label, int weight) {
        this.label = label;
        this.weight = weight;
    }
    
    @Override
    public int hashCode() {
        return (label == null) ? 0 : label.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (label == null) {
            if (other.label != null)
                return false;
        } else if (!label.equals(other.label))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return label;
    }
}
