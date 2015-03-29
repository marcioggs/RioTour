package br.com.riotour.listener;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

public class LigarTelefoneClickListener implements View.OnClickListener {

	Context ctx;

	/**
	 * Construtor.
	 * @param ctx Contexto
	 */
	public LigarTelefoneClickListener(Context ctx) {
		this.ctx = ctx;
	}

	@Override
	public void onClick(View v) {
		TextView text = (TextView) v;
		String telefone = text.getText().toString();

		Intent intent = new Intent(Intent.ACTION_DIAL);
		intent.setData(Uri.parse("tel:" + telefone));
		ctx.startActivity(intent);
	}
}
