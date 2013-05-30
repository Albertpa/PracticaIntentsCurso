package com.aprendeandroid.practica_intents;

import java.io.File;
import java.util.Locale;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListaActivity extends Activity {
	
	private final int RC_HACER_FOTO = 1;
	private final int SELECT_PHOTO = 2;
	private String image_path = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_lista);
		
		ListView menuList = (ListView) findViewById(R.id.listViewBotones);
		
		String[] items = { 
				getResources().getString(R.string.NavegarWeb),
				getResources().getString(R.string.contactos),
				getResources().getString(R.string.llamar),
				getResources().getString(R.string.mensaje),
				getResources().getString(R.string.google),
				getResources().getString(R.string.mail),
				getResources().getString(R.string.camara),
				getResources().getString(R.string.galeria),
				getResources().getString(R.string.mapas)
		};
		
		
		ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, R.layout.menu_item, items);		
		menuList.setAdapter(adapt);
		
		menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {	
				
			
				//llamamos a lanzarActivity, para cada una de las posiciones 
				//posibles como entero
				//lanzarActivityPos(position);		
				

				//O llamamos a lanzarActivity (ya modificada), para cada una de las posiciones posibles 
				//pasandole la view itemClicked				
				lanzarActivityStr(itemClicked);
				
			}
		});
		
		
	}


	
	
	
	//intents implicitos usando la posicion, es un poco engorroso y hace falta hacer varias pruebas para
	//ordenarlo correctamente
	public void lanzarActivityPos(int posicion){
		
		Intent i;
		switch(posicion){
			case 0:				
				i = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.aprendeandroid.es") );
				startActivity(i);
			break;
			
			case 1:				
				i = new Intent(Intent.ACTION_VIEW,Uri.parse("content://contacts/people") );
				startActivity(i);
			break;
			
			case 2:
				i = new Intent(Intent.ACTION_VIEW,Uri.parse("tel:123456789") );
				startActivity(i);
				break;
				
			case 3:
				i = new Intent(Intent.ACTION_VIEW,Uri.parse("sms:123456789") );
				startActivity(i);
				break;
				
			case 4:
				i = new Intent(Intent.ACTION_WEB_SEARCH);
				i.putExtra(SearchManager.QUERY, "android");
				startActivity(i);
				break;
				
			case 8:  //Solo con google apis, sino va al catch
				try{
					Class.forName("com.google.android.maps.MapActivity");
					
					//Simulamos que traemos datos de posicion
					float latitud = 40.4f; //Atocha
					float longitud= -3.7f;
					int zoom = 15;
					
					String geoURI = String.format(Locale.US, "geo:%f, %f?z=%d", latitud, longitud, zoom); //float, float, double
					
					i = new Intent(Intent.ACTION_VIEW,Uri.parse(geoURI) );				
					startActivity(i);
					
				}catch (Exception e){
					Toast.makeText(this, "No tienes google apis", Toast.LENGTH_LONG).show();
				}
				
				break;
				
			case 5: //solo con google Apis en el emulador
				i = new Intent(Intent.ACTION_SEND);
				i.setType("message/rfc822");
				i.putExtra(Intent.EXTRA_EMAIL, new String [] {"test@gmail.com"});
				i.putExtra(Intent.EXTRA_SUBJECT, "subject goes here");
				i.putExtra(Intent.EXTRA_TEXT, "body goes here");
				
				//para que el usuario elija la aplicacion
				startActivity(Intent.createChooser(i,"Select email application."));
				break;
				
			case 6:
				i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				
				//ruta del fichero temporal en (mnt/)sdcard como String
				image_path = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/tmp_image.jpg";
				File tmpFile = new File(image_path); //Creamos el objeto File que representa el fichero temporal
				
				Uri uri = Uri.fromFile(tmpFile); //pasamos el path del fichero a URI
				i.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri); //y se la pasamos al Intent para lanzar la camara
				//fin para high resolution
				
				startActivityForResult(i, RC_HACER_FOTO);
				break;
				
	        case 7:
				i = new Intent(Intent.ACTION_PICK);
				i.setType("image/*");
				startActivityForResult(i, SELECT_PHOTO);
				break;
			
		}
	}
	
	
	 //intents implicitos usando la view que le pasamos
	 //
	 //intents implicitos	  
	
	public void lanzarActivityStr(View v){
		
		TextView textViewPulsado = (TextView) v;
		String strText = textViewPulsado.getText().toString();
		Intent i;
		//Log.i("PracticaIntents", strText);
		
		if(strText.equalsIgnoreCase(getResources().getString(R.string.NavegarWeb))){		
	
			i = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.aprendeandroid.es") );
			startActivity(i);
			
		}else if(strText.equalsIgnoreCase(getResources().getString(R.string.contactos))){	
			
			i = new Intent(Intent.ACTION_VIEW,Uri.parse("content://contacts/people") );
			startActivity(i);
			
		}else if(strText.equalsIgnoreCase(getResources().getString(R.string.llamar))){	
			
			i = new Intent(Intent.ACTION_VIEW,Uri.parse("tel:123456789") );
			startActivity(i);
			
		}else if(strText.equalsIgnoreCase(getResources().getString(R.string.mensaje))){	
			
			i = new Intent(Intent.ACTION_VIEW,Uri.parse("sms:123456789") );
			startActivity(i);
			
		}else if(strText.equalsIgnoreCase(getResources().getString(R.string.google))){	
			
			i = new Intent(Intent.ACTION_WEB_SEARCH);
			i.putExtra(SearchManager.QUERY, "android");
			startActivity(i);
			
		}else if(strText.equalsIgnoreCase(getResources().getString(R.string.mail))){
			
			//solo con google Apis en el emulador
			i = new Intent(Intent.ACTION_SEND);
			i.setType("message/rfc822");
			i.putExtra(Intent.EXTRA_EMAIL, new String [] {"test@gmail.com"});
			i.putExtra(Intent.EXTRA_SUBJECT, "subject goes here");
			i.putExtra(Intent.EXTRA_TEXT, "body goes here");
			
			//para que el usuario elija la aplicacion
			startActivity(Intent.createChooser(i,"Select email application."));
			
		}else if(strText.equalsIgnoreCase(getResources().getString(R.string.camara))){		
			
			i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			
			//ruta del fichero temporal en (mnt/)sdcard como String
			image_path = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/tmp_image.jpg";
			File tmpFile = new File(image_path); //Creamos el objeto File que representa el fichero temporal
			
			Uri uri = Uri.fromFile(tmpFile); //pasamos el path del fichero a URI
			i.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri); //y se la pasamos al Intent para lanzar la camara
			//fin para high resolution
			
			startActivityForResult(i, RC_HACER_FOTO);
			
		}else if(strText.equalsIgnoreCase(getResources().getString(R.string.galeria))){	
			
			i = new Intent(Intent.ACTION_PICK);
			i.setType("image/*");
			startActivityForResult(i, SELECT_PHOTO);
			
		}else if(strText.equalsIgnoreCase(getResources().getString(R.string.mapas))){
			
			//Solo con google apis, sino va al catch
			try{
				Class.forName("com.google.android.maps.MapActivity");
				
				//Simulamos que traemos datos de posicion
				float latitud = 40.4f; //Atocha
				float longitud= -3.7f;
				int zoom = 15;
				
				String geoURI = String.format(Locale.US, "geo:%f, %f?z=%d", latitud, longitud, zoom); //float, float, double
				
				i = new Intent(Intent.ACTION_VIEW,Uri.parse(geoURI) );				
				startActivity(i);
				
			}catch (Exception e){
				Toast.makeText(this, "No tienes google apis", Toast.LENGTH_LONG).show();
			}
			
		}
		

		
	}
	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		
		//Si retorna un resultado correcto
		
		//Dependiendo de la actividad llamada que ha retornado el resultado
		switch(requestCode){
			
			case RC_HACER_FOTO:
				if (resultCode == RESULT_OK){
					ImageView view = (ImageView) findViewById(R.id.contenedorImagen);
					
					Bitmap cameraPic =  scaleBitmap(image_path, view.getHeight());
					//fin high resolution
					
					if(cameraPic != null){
						view.setImageBitmap(cameraPic);
						
					}else{
						Toast toast = Toast.makeText(this, "Foto fallida",
							Toast.LENGTH_LONG);
						toast.show();
						
					}
				}
				break;
				
			case SELECT_PHOTO:
				
				if(resultCode == RESULT_OK){
					ImageView view = (ImageView) findViewById(R.id.contenedorImagen);
					
					Uri imageUri = data.getData();
					Bitmap galleryPic = scaleBitmap(getPathFromUri(imageUri), view.getHeight());
					
					if(galleryPic != null){
						view.setImageBitmap(galleryPic);
						
					}else{
						Toast toast = Toast.makeText(this, "Imagen fallida", Toast.LENGTH_LONG);
						toast.show();
					}
					
				}
				break;
			
		}
		
	}
	
	/**
	 * Convierte una URI generada por el provider MediaStore (base de datos SQL de archivos media)
	 * p. ej. content://media/external/images/media/36
	 * en un path del sistema de ficheros, p. ej. /mnt/sdcard/DCIM/Camera/IMG_20121127_053546.jpg
	 * @param uri la URI
	 * @return el path
	 */
	private String getPathFromUri(Uri uri) { 
		String path = "";
		String[] projection = { MediaColumns.DATA };
		Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
		try {
			int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
			if (cursor.moveToFirst())
				path = cursor.getString(column_index);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cursor.close();
		return path;
	}
	

		//metodo de rescalado
	private Bitmap scaleBitmap(String image_path, int maxDimension) {
		Bitmap scaledBitmap;
		
		BitmapFactory.Options op = new BitmapFactory.Options();
		op.inJustDecodeBounds = true; // solo devuelve las dimensiones, no carga bitmap
		scaledBitmap = BitmapFactory.decodeFile(image_path, op); //en op est‡n las dimensiones

		// usamos Math.max porque es mejor que la imagen sea un poco mayor que el
		// control donde se muestra, que un poco menor. Ya que si es menor el control
		// la agranda para ajustarla y se podria pixelar un poco.
		if ((maxDimension < op.outHeight) || (maxDimension < op.outWidth)) {
			// cada dimensiÃ³n de la imagen se dividir por op.inSampleSize al cargar
			op.inSampleSize = Math.round(Math.max((float) op.outHeight / (float) maxDimension,(float) op.outWidth / (float) maxDimension)); //calculamos la proporcion de la escala para que no deforme la imagen y entre en las dimensiones fijadas en la vista
		}

		op.inJustDecodeBounds = false; // ponemos a false op...
		scaledBitmap = BitmapFactory.decodeFile(image_path, op); //...para que ya el bitmap se cargue realmente
		
		return scaledBitmap;
	}
	
	@Override
	protected void onStop() { //para high resolution
		// TODO Auto-generated method stub
		super.onStop();
		if(image_path != null){
			File img = new File(image_path);
			img.delete();
		}
	}

}
