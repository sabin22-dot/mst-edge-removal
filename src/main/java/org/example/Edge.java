package org.example;
public class Edge implements Comparable<Edge> {
    private final int u;
    private final int v;
    private final int weight;

    public Edge(int u, int v, int weight) {
        if (u == v) {
            throw new IllegalArgumentException("Self-loops are not allowed in this graph");
        }
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public int getU() {
        return u;
    }

    public int getV() {
        return v;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return u + " - " + v + " : " + weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;

        Edge edge = (Edge) o;
        return weight == edge.weight &&
                ((u == edge.u && v == edge.v) || (u == edge.v && v == edge.u));
    }

    @Override
    public int hashCode() {
        int min = Math.min(u, v);
        int max = Math.max(u, v);
        int result = min;
        result = 31 * result + max;
        result = 31 * result + weight;
        return result;
    }
}
