package model.data_structures;

public class TablaHash <K extends Comparable <K>, V >  implements ITablaSimbolos <K , V>
{
	private static final int INITIAL_SIZE = 500000;

	private int actSize;

	private int maxSize;

	private ArregloDinamico<NodeTH<K,V>> nodes;

	public TablaHash ()
	{
		nodes = new ArregloDinamico <NodeTH<K,V>> (INITIAL_SIZE);
		maxSize = INITIAL_SIZE;
	} 

	public void put (K key, V value)
	{
		int keyHash = hash(key);
		if (value == null) 
		{
			System.out.println("NULO");
			remove(key);
			return;
		}
		
		else if (nodes.getElement(keyHash) == null)
		{
			actSize++;
			NodeTH <K,V> nodoNuevo = new NodeTH <K,V>(key, value, null);
			nodes.insertElement(nodoNuevo, keyHash);
			return;
		}
		
		else if (nodes.getElement(keyHash) != null)
		{
			for (NodeTH<K,V> i = nodes.getElement(keyHash); i != null; i = i.getNext()) 
			{
				if (key.compareTo(i.getKey()) == 0) 
				{
					i.setValue(value);
					return;
				}
			}
			actSize++;
			NodeTH <K,V> nodoNuevo = new NodeTH <K,V>(key, value, nodes.getElement(keyHash));
			nodes.getElement(keyHash).setPrev(nodoNuevo);
			nodes.insertElement(nodoNuevo, keyHash);
		}
	}

	public V get (K key)
	{
		int keyHash = hash(key);
		V wantedValue = null;
		for (NodeTH<K,V> i = nodes.getElement(keyHash); i != null; i = i.getNext()) 
		{
			if (i.getKey().compareTo(key) == 0) 
			{
				wantedValue = i.getValue();
			}
		}
		return wantedValue;
	}

	public V remove (K key)
	{
		int keyHash = hash(key);
		V wantedValue = null;
		if (nodes.getElement(keyHash).getKey().compareTo(key) == 0)
		{
			nodes.insertElement(nodes.getElement(keyHash).getNext(), keyHash);
			wantedValue = nodes.getElement(keyHash).getValue();
			actSize --;
		}
		else
		{
			for (NodeTH<K,V> i = nodes.getElement(keyHash).getNext(); i != null; i = i.getNext()) 
			{
				if (key.compareTo(i.getKey()) == 0) 
				{
					i.getPrev().setNext(i.getNext());
					wantedValue = i.getValue();
					actSize --;
				}
			}
		}
		return wantedValue;
	}

	public boolean contains (K key)
	{
		return get (key) != null;
	}

	public boolean isEmpty()
	{
		return size() == 0;
	}

	public int size()
	{
		return actSize;
	}

	public ILista <K> keySet()
	{
		ArregloDinamico <K> keyList = new ArregloDinamico <K> (size());
		for (int i = 0; i < size(); i++)
		{
			for(NodeTH<K,V> j = nodes.getElement(i); j != null; j = j.getNext())
			{
				keyList.addLast(j.getKey());		
			}
		}
		return keyList;
	}

	//	public ILista <V> valueSet()
	//	{
	//		ArregloDinamico <V> valueList = new ArregloDinamico <V> (size());
	//		for (int i = 0; i < size(); i++)
	//		{
	//			for(NodeTH<K,V> j = nodes[i]; j != null; j = j.getNext())
	//			{
	//				valueList.addLast(j.getValue());		
	//			}
	//		}
	//		return valueList;
	//	}

	//entre 1 y M
	public int hash (K key)
	{
		return (Math.abs(key.hashCode()) % maxSize) + 1;
	}
}
