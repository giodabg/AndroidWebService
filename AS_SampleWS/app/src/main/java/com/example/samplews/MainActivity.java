package com.example.samplews;

// http://kobjects.org/ksoap2/doc/api/org/ksoap2/package-summary.html
// http://simpligility.github.io/ksoap2-android/users.html
// http://amslaurea.unibo.it/3569/1/DeAngelis_Sebastiano_MetodologieEStrumentiDiAnticontraffazioneInAmbientiAndroid_.pdf
// http://143.225.81.37/www.mobilab.unina.it/tesi/Tesi_Guazzo.pdf

// http://www.thecrazyprogrammer.com/2016/11/android-soap-client-example-using-ksoap2.html
// https://www.codeproject.com/Tips/810432/Android-deserialize-KSoap-response-into-Complex-o
// http://binarylifebyanjula.blogspot.it/2013/11/android-webservices-ksoap2-complex.html
// http://stackoverflow.com/questions/8533390/how-to-retrieve-array-of-objects-as-a-result-from-ksoap-web-service-in-android?rq=1
// http://www.thejavaprogrammer.com/java-soap-web-services-tutorial/


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


public class MainActivity extends Activity {

    private EditText editTextNumero1;
    private EditText editTextNumero2;
    private TextView textViewRisultato;
    private Spinner spinnerOperazione;
    private Button buttonEsegui;

    // gestore degli elementi presenti nello spinner
    private static ArrayAdapter<String> dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumero1 = (EditText) findViewById(R.id.editTextNumero1);
        editTextNumero2 = (EditText) findViewById(R.id.editTextNumero2);
        textViewRisultato = (TextView) findViewById(R.id.textViewRisultato);
        spinnerOperazione = (Spinner) findViewById(R.id.spinnerOperazione);


        // https://www.mkyong.com/android/android-spinner-drop-down-list-example/
        // http://stackoverflow.com/questions/16693941/spinner-text-size-does-not-change

        // aggiungo allo spinner il gestore degli elementi
        addAdapterToSpinner(spinnerOperazione);

        // aggiungo allo spinner il gestore dell'evento associato alla modifica dell'elemento selezionato
        spinnerOperazione.setOnItemSelectedListener(new CustomOnItemSelectedListener());


        // creo oggetto per comunicare con il web service che lavora in un thread di tipo AsyncTask
        CallWebService callWS = new CallWebService();

        // richiamo il thread di tipo AsyncTask chiedendo l'esecuzione del web service
        // primo parametro: identificatore dell'operazione da richiamare
        // in questo caso si recuperano i valori da inserire nello spinner
        callWS.execute(CallWebService.METHOD_NAME_LIST);


        // aggiungo al bottone il gestione evento click
        buttonEsegui = (Button) findViewById(R.id.buttonEsegui);
        buttonEsegui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // creo oggetto per comunicare con il web service che lavora in un thread di tipo AsyncTask
                CallWebService callWs = new CallWebService();

                // imposto textView per permettere al thread che comunica con il web service di visualizzare il risultato
                callWs.setTextViewRisultato(textViewRisultato);

                // richiamo il thread di tipo AsyncTask chiedendo l'esecuzione del web service
                // primo parametro: identificatore dell'operazione da richiamare
                // secondo e terzo parametro i valori numerici da inviare
                callWs.execute(CallWebService.METHOD_NAME_ADD, editTextNumero1.getText().toString(), editTextNumero2.getText().toString());
            }
        });

    }

    // aggiungo un gestore dei dati presenti nello spinner
    public void addAdapterToSpinner(Spinner spinner) {

        dataAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, new ArrayList<String>());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    // http://www.mysamplecode.com/2012/10/android-spinner-programmatically.html
    // add items into spinner dynamically
    public static void addItemToSpinner(String myData) {
        dataAdapter.add(myData);
    }

    // notifico modifica elementi al gestore della lista di elementi presenti nello spinner
    public static void notifyDataSetChanged() {
        dataAdapter.notifyDataSetChanged();
    }

    // public class per gestire la selezione di un elemento dello spinner
    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            String str = parent.getItemAtPosition(pos).toString();

            // visualizzo l'elemento selezionato
            Toast.makeText(MainActivity.this, "onItemSelected: " + str, Toast.LENGTH_SHORT).show();
        }

        // http://stackoverflow.com/questions/16439463/why-is-onnothingselected-method-needed-in-spinner-listener
        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            Toast.makeText(MainActivity.this, "NothingSelected", Toast.LENGTH_SHORT).show();
        }
    }

}
