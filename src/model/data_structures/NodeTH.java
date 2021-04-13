package model.data_structures;

public class NodeTH <K extends Comparable <K>, V > implements Comparable <NodeTH<K, V>>
{
	private K key;
	
	private V value;
	
	private NodeTH<K,V> prev;
	
	private NodeTH<K,V> next;
	
	public NodeTH (K pKey, V pValue, NodeTH<K,V> pNext)
	{
		key = pKey;
		value = pValue;
		next = pNext;
		prev = null;
	}
	
	public K getKey ()
	{
		return key;
	}
	
	public V getValue ()
	{
		return value;
	}
	
	public NodeTH<K,V> getPrev()
	{
		return prev;
	}
	
	public NodeTH<K,V> getNext()
	{
		return next;
	}
	
	public void setKey (K pKey)
	{
		key = pKey;
	}
	
	public void setValue (V pValue)
	{
		value = pValue;
	}
	
	public void setNext(NodeTH<K, V> pNext)
	{
		next = pNext;
	}
	
	public void setPrev(NodeTH<K, V> pPrev)
	{
		prev = pPrev;
	}

	public int compareTo (NodeTH<K, V> another)
	{
		return key.compareTo(another.getKey());
	}

}
