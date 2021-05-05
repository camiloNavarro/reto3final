package model.utils;

import java.util.Comparator;

import model.data_structures.ILista;
import model.logic.Audio;

public final class Ordenamiento <T extends Comparable<T>>

{
	/** Ordenamiento de N elementos en posiciones [1, N], con criterio de comparacion, ascendentemente o descendentemente  */
	public void ordenarInsercion (ILista<Audio> lista,Comparator<Audio> comparadorXistrumental, boolean ascendente)
	{
		int n = lista.size();
		for (int i = 1+1; i <= n; i++)
		{
			boolean enPosicion = false;
			for (int j = i; j > 1 && !enPosicion; j -= 1)
			{
				int factorComparacion = (ascendente?1:-1) * comparadorXistrumental.compare(lista.getElement(j), lista.getElement(j-1));
				if (factorComparacion < 0)
					lista.exchange(j, j-1);
				else
					enPosicion = true;
			}
		}
	}
	
	/** Ordenamiento de N elementos en posiciones [1, N], con criterio de comparacion, ascendentemente o descendentemente  */
	public final void ordenarShell(ILista<Audio> lista, Comparator<Audio> comparadorXistrumental, boolean ascendente)
	{
		int n = lista.size();
		int h = 1;
		while (h < n/3)
			h = 3 * h + 1;

		while (h >=1)
		{
			// generalizacion del alg. Insertion sort con un valor h >= 1
			for (int i = h+1; i <= n; i++)
			{
				boolean enPosicion = false;
				for (int j = i; j > h && !enPosicion; j -= h)
				{
					int factorComparacion = (ascendente?1:-1) * comparadorXistrumental.compare(lista.getElement(j), lista.getElement(j-h));
					if (factorComparacion < 0)
						lista.exchange(j, j-h);
					else
						enPosicion = true;
				}
			}
			h /= 3;
		}
	}



	/**
	 * Método que va dejando el pivot en su lugar, mientras mueve elementos menores
	 * a la izquierda del pivot y elementos mayores a la derecha del pivot.
	 */
	private final int partition(ILista<Audio> lista, Comparator<Audio> comparadorXistrumental, boolean ascendente, int lo, int hi)
	{
		int follower, leader;
		follower = leader = lo;
		while (leader < hi)
		{
			int factorComparacion = (ascendente?1:-1) * comparadorXistrumental.compare(lista.getElement(leader), lista.getElement(hi));
			if(factorComparacion < 0)
			{
				lista.exchange(follower, leader);
				follower ++;
			}
			leader ++;
		}
		lista.exchange(follower, hi);
		return follower;
	}

	/**
	 * Se localiza el pivot, utilizando el método de partición.
	 * Luego se hace la recursión con los elementos a la izquierda del pivot
	 * y los elementos a la derecha del pivot.
	 */
	private final void sort(ILista<Audio> lista, Comparator<Audio> comparador, boolean ascendente, int lo, int hi)
	{
		if(lo >= hi)
			return;
		int pivot = partition(lista, comparador, ascendente, lo, hi);
		sort(lista, comparador, ascendente, lo, pivot - 1);
		sort(lista, comparador, ascendente, pivot +1, hi);
	}
	

	/**
	 * Método de entrada, lanza el quick sort recursivo.
	 */
	public final void ordenarQuickSort(ILista<Audio> lista, Comparator<Audio> comparadorXistrumental, boolean ascendente)
	{
		sort(lista, comparadorXistrumental, ascendente, 1, lista.size());
	}


}
