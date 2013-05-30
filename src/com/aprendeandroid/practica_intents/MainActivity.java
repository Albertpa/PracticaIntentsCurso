package com.aprendeandroid.practica_intents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	/*Albert Pagès Raventos
	 * En su momento añadí 2 campos más para practicar (apellidos y edad)
	 * he creído oportuno dejarlos	 
	*/
	private final String degTag = "PracticaIntents";
	private final int RC_EDITAR_CAMPOS = 1;
	
	//elementos de la vista
		private TextView campoPantallaA;
		private TextView campoPantallaB;
		private TextView campoPantallaC;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		campoPantallaA = (TextView) findViewById(R.id.campoTexto);
		campoPantallaB = (TextView) findViewById(R.id.campoTexto2);
		campoPantallaC = (TextView) findViewById(R.id.campoTexto3);
		
		Log.i(degTag, "onCreate()");
	}
	
	
	//LANZAMIENTO DE INTENT CON EXTRAS
	public void irActivityDialog(View v){
	
		
		//Recojo el valor nombre
		EditText ed = (EditText) findViewById(R.id.campoTexto);
		String nombre = ed.getText().toString();
		
		if(nombre.trim().equals("")){
			nombre = "No establecido";
		}
		
		//Recojo el valor apellido
		EditText ed2 = (EditText) findViewById(R.id.campoTexto2);
		String apellido = ed2.getText().toString();
		
		if(apellido.trim().equals("")){
			apellido = "No establecido";
		}
		
		//Recojo el valor edad
		EditText ed3 = (EditText) findViewById(R.id.campoTexto3);
		String edad = ed3.getText().toString();
		
		if(edad.trim().equals("")){
			edad = "No establecido";
		}
		
		Intent i;
		//llamar a ActivityDialog
		i = new Intent(this, DialogActivity.class);	
		i.putExtra("nombre", nombre);
		i.putExtra("apellido", apellido);
		i.putExtra("edad", edad);
		//startActivity(i);
		
		//lanzamos la Activity pero necesitamos recoger valores
		startActivityForResult(i, RC_EDITAR_CAMPOS);
		
	}
	
	//LANZAMIENTO DE ACTIVITY CON LISTA DE INTENT IMPLICITOS
	public void irActivityList(View v){
		Intent i;
		i = new Intent(this, ListaActivity.class);
		startActivity(i);
	}
	
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Si retorna un resultado correcto
		
		//Dependiendo de la actividad llamada que ha retornado el resultado
		switch (requestCode) {
		case RC_EDITAR_CAMPOS:
			
			if(resultCode == RESULT_OK){
				
				//se editan los valores de la vista
					if(data.hasExtra("nombreEditado")){			
						campoPantallaA.setText(""+data.getStringExtra("nombreEditado"));
					}
					if(data.hasExtra("apellidoEditado")){			
						campoPantallaB.setText(""+data.getStringExtra("apellidoEditado"));
					}
					if(data.hasExtra("edadEditado")){			
						campoPantallaC.setText(""+data.getStringExtra("edadEditado"));
					}
					
			}
			/*
			else if(resultCode == RESULT_CANCELED){
				//no se hace nada, se cierra el dialog apretando cancelar
			
			}*/
			break;		
		}
		
	}

	
	
	
	
	
	
	//CICLO DE VIDA ACTIVITY
	
	

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i(degTag, "onStart()");
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i(degTag, "onResume()");
	}
	
	
	
	
	
	
	
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.i(degTag, "onRestart()");
	}
	
	
	
	
	

	

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i(degTag, "onPause()");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i(degTag, "onStop()");
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(degTag, "onDestroy()");
	}

	
	

	

	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Log.i(degTag, "onBackPressed()");
	}

	
	
	

}
