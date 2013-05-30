package com.aprendeandroid.practica_intents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DialogActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog);
		
		String nombre = "";
		String apellido = "";
		String edad = "";
		
		//nombre
		if(getIntent().hasExtra("nombre")){
			//Si existe, Recojemos el valor del intent que lo lanza, es decir, el intent de MainActivity
			nombre = getIntent().getStringExtra("nombre");
		}
		TextView tv = (TextView) findViewById(R.id.textViewNombre);
		tv.setText(nombre);
		
		//apellido
		if(getIntent().hasExtra("apellido")){			
			apellido = getIntent().getStringExtra("apellido");
		}
		TextView tv2 = (TextView) findViewById(R.id.textViewApellido);
		tv2.setText(apellido);
		
		//edad
		if(getIntent().hasExtra("edad")){			
			edad = getIntent().getStringExtra("edad");
		}
		TextView tv3 = (TextView) findViewById(R.id.textViewEdad);
		tv3.setText(edad);
		
	}
	
	
	
	public void onButtonClick(View v){
		
		//Recojo el valor nombre editado
		EditText ed = (EditText) findViewById(R.id.textViewNombre);
		String nombreEditado = ed.getText().toString();
				
				
		//Recojo el valor apellido editado
		EditText ed2 = (EditText) findViewById(R.id.textViewApellido);
		String apellidoEditado = ed2.getText().toString();
				
		//Recojo el valor edad editado
		EditText ed3 = (EditText) findViewById(R.id.textViewEdad);
		String edadEditado = ed3.getText().toString();
				
		switch (v.getId()) {
		case R.id.OK:
			Intent i;
			/**
			 * Del foro de dudas --->
			 * Es preferible usar el constructor vacío Intent(), porque digamos DialogActivity 
			 * sabe que tiene que poner this y que MainActivity la lanzó, al terminar de rellenar 
			 * el Intent creado en setResult() antes de pasarlo a MainActivity. No hay otra posibilidad,
			 *  no va a lanzar otra Activity distinta para devolverle el resultado. 
			 *  Si usamos el otro constructor de Intent, DialogActivity sobreescribirá sus parámetros antes de pasarlo a MainActivity.
			 */
			i = new Intent();
			i.putExtra("nombreEditado", nombreEditado);
			i.putExtra("apellidoEditado", apellidoEditado);
			i.putExtra("edadEditado", edadEditado);				
			setResult(RESULT_OK, i);
			finish();
			
			break;
			
		case R.id.Cancel:			
			setResult(RESULT_CANCELED);
			finish();
			break;
		
		}
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_dialog, menu);
		return true;
	}

}
