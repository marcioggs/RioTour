package br.com.riotour.listener;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

public class EnviarEmailClickListener implements View.OnClickListener {

	Context ctx;

	/**
	 * Construtor.
	 * @param ctx Contexto
	 */
	public EnviarEmailClickListener(Context ctx) {
		this.ctx = ctx;
	}

	@Override
	public void onClick(View v) {
		TextView text = (TextView) v;
		String email = text.getText().toString();

		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("message/rfc822");
		i.putExtra(Intent.EXTRA_EMAIL  , new String[]{email});
		ctx.startActivity(i);
	}
}
