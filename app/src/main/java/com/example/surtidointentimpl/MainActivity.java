package com.example.surtidointentimpl;

import android.Manifest;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import androidx.core.app.ActivityCompat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

	    Button btn1 = findViewById(R.id.button1);
	    Button btn2 = findViewById(R.id.button2);
	    Button btn3 = findViewById(R.id.button3);
	    Button btn4 = findViewById(R.id.button4);
	    Button btn5 = findViewById(R.id.button5);
	    Button btn6 = findViewById(R.id.button6);

	    btn1.setOnClickListener(this);
	    btn2.setOnClickListener(this);
	    btn3.setOnClickListener(this);
	    btn4.setOnClickListener(this);
	    btn5.setOnClickListener(this);
	    btn6.setOnClickListener(this);

		if (Build.VERSION.SDK_INT >= 23)
			if (! ckeckPermissions())
			  requestPermissions();
	}

	@Override
	public void onClick (View v) {
		Intent in;
		final String lat = getString(R.string.lat);
		final String lon = getString(R.string.lon);
		final String url = getString(R.string.url);
		final String address = getString(R.string.direccion);
		final String textToSearch = getString(R.string.textoABuscar);

		switch (v.getId()) {
			//Localizacion coordenadas
			case R.id.button1:
				Toast.makeText(this, getString(R.string.opcion1), Toast.LENGTH_LONG).show();
				in = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + lat + ',' + lon));
				startActivity(in);
				break;
			//Localizacion direccion
			case R.id.button2:
				Toast.makeText(this, getString(R.string.opcion2), Toast.LENGTH_LONG).show();
				in = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + address));
				startActivity(in);
				break;
			//Accediendo web
			case R.id.button3:
				Toast.makeText(this, getString(R.string.opcion3), Toast.LENGTH_LONG).show();
				in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				startActivity(in);
				break;
			//Buscando en google
			case R.id.button4:
				Toast.makeText(this, getString(R.string.opcion4), Toast.LENGTH_LONG).show();
				in = new Intent(Intent.ACTION_WEB_SEARCH);
				in.putExtra(SearchManager.QUERY, textToSearch);
				startActivity(in);
				break;
			//Llamar
			case R.id.button5:
				Toast.makeText(this, getString(R.string.opcion5), Toast.LENGTH_LONG).show();
				in = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + getText(R.string.telef)));
				startActivity(in);
				break;
			//Acceder contactos
			case R.id.button6:
				Toast.makeText(this, getString(R.string.opcion6), Toast.LENGTH_LONG).show();
				in = new Intent(Intent.ACTION_VIEW);
				in.setData(ContactsContract.Contacts.CONTENT_URI);
				startActivity(in);
				break;
			//Marcar
			case R.id.button7:
				Toast.makeText(this, getString(R.string.ToastMarcar), Toast.LENGTH_LONG).show();
			//Enviar SMS
			case R.id.button8:
				Toast.makeText(this, getString(R.string.ToastSMS), Toast.LENGTH_LONG).show();
			//Enviar correo
			case R.id.button9:
				Toast.makeText(this, getString(R.string.ToastCorreo), Toast.LENGTH_LONG).show();
			//Acceder galeria
			case R.id.button10:
				Toast.makeText(this, getString(R.string.ToastGaleria), Toast.LENGTH_LONG).show();
		}
	}


	@Override
	protected void onResume() {
		super.onResume();
		if (Build.VERSION.SDK_INT >= 23)
			if (! ckeckPermissions())
				requestPermissions();
	}

	private boolean ckeckPermissions() {
		if (Build.VERSION.SDK_INT >= 23) {
			return ckeckPermissionsCallPhone() && ckeckPermissionsReadContacts();
		    }
		else
			return true;
	}

	private boolean ckeckPermissionsCallPhone() {
		return ActivityCompat.checkSelfPermission(getApplicationContext(),
				Manifest.permission.CALL_PHONE) ==
				PackageManager.PERMISSION_GRANTED;
	}

	private boolean ckeckPermissionsReadContacts() {
		return ActivityCompat.checkSelfPermission(getApplicationContext(),
				Manifest.permission.READ_CONTACTS) ==
				PackageManager.PERMISSION_GRANTED;
	}

	private void requestPermissions() {
		ActivityCompat.requestPermissions(MainActivity.this,
				new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE},
				0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
