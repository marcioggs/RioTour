package br.com.riotour.activity.detalhe;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.common.base.Strings;

import java.util.ArrayList;

public abstract class DetalheFragment extends Fragment {

	/**
	 * Esconde todos os campos de dados com valor vazio.
	 * @param v View pai
	 */
	protected void esconderCamposVazios(View v) {
		ArrayList<View> filhas = getAllChildren(v);

		for (View filha : filhas) {
			boolean esconder = false;

			if (filha instanceof TextView) {

				TextView texto = (TextView) filha;
				if (texto.getText() != null &&
						Strings.isNullOrEmpty(texto.getText().toString())) {
					esconder = true;
				}
			} else if (filha instanceof RatingBar) {

				RatingBar bar = (RatingBar) filha;
				if (bar.getRating() == 0f) {
					esconder = true;
				}
			}

			if (esconder) {
				View pai = (View) filha.getParent();
				pai.setVisibility(View.GONE);
			}
		}
	}

	/**
	 * Obtém todas as views filhas.
	 * @param v View pai
	 * @return Views filhas
	 */
	private ArrayList<View> getAllChildren(View v) {

		if (!(v instanceof ViewGroup)) {
			ArrayList<View> viewArrayList = new ArrayList<View>();
			viewArrayList.add(v);
			return viewArrayList;
		}

		ArrayList<View> result = new ArrayList<View>();

		ViewGroup viewGroup = (ViewGroup) v;
		for (int i = 0; i < viewGroup.getChildCount(); i++) {

			View child = viewGroup.getChildAt(i);

			ArrayList<View> viewArrayList = new ArrayList<View>();
			viewArrayList.add(v);
			viewArrayList.addAll(getAllChildren(child));

			result.addAll(viewArrayList);
		}
		return result;
	}
}
