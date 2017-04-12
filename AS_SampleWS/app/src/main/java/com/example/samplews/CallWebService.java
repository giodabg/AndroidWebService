package com.example.samplews;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

// AsyncTask problem with orientation https://androidresearch.wordpress.com/2013/05/10/dealing-with-asynctask-and-screen-orientation/

/**
 * Created by Gio on 22/03/2017.
 */

// Uso di AsyncTask https://androidresearch.wordpress.com/2012/03/17/understanding-asynctask-once-and-forever/

//  AsyncTask is a generic class, it uses 3 types: AsyncTask<Params, Progress, Result>.
//        Params – the input. what you pass to the AsyncTask
//        Progress – if you have any updates, passed to onProgressUpdate()
//        Result – the output. what returns doInBackground()
public class CallWebService extends AsyncTask<String, String, String> {

    // vettore dove memorizzare la lista dei valori restituiti dal web service
    private ArrayList<String> arraylist;

    // TextView dove visualizzare il risultato
    private TextView textViewRisultato;

    // URL corrispondente al web service
    public final static String URL = "http://10.0.2.2:8080/NB_WSApplicationTestDB/WSApplicationTestDB";
    // target name space
    public final static String NAMESPACE = "http://application.giodabg.org/";

    // nome dell'operazione
    public final static String METHOD_NAME_LIST = "getList";
    // nome dei parametri
    private static final String PARAMETER_NAME_LIST1 = "nome";
    // valore per il parametro
    private static final String PARAMETER_VALUE_LIST1 = "operazioni";

    // nome dell'operazione
    public final static String METHOD_NAME_ADD = "somma";
    // nome dei parametri
    private static final String PARAMETER_NAME_ADD1 = "num1";
    private static final String PARAMETER_NAME_ADD2 = "num2";

    // nome dell'operazione
    public final static String METHOD_NAME_SQRT = "radiceQuadrata";
    // nome dei parametri
    private static final String PARAMETER_NAME_SQRT1 = "num1";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Things to be done before execution of long running operation. For
        // example showing ProgessDialog

        // creazione vettore per memorizzare il o i valori ricevuti dal web service
        arraylist = new ArrayList<String>();
    }


    @Override
    protected String doInBackground(String... params) {
        String str;

        if (params[0].equals(METHOD_NAME_ADD)) {

            // impostazione nome esteso dell'operazione
            String SOAP_ACTION = NAMESPACE + METHOD_NAME_ADD;

            // creazione dell'oggetto per la comunicazione con il web service
            // impostando il namespace e l'operazione da eseguire
            SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_NAME_ADD);

            // preparazione delle proprietà/parametri dell'operazione
            PropertyInfo propertyInfo = new PropertyInfo();

            // FORMA ESTESA: aggiunta di un parametro con nome PARAMETER_NAME_ADD1 di tipo int
            propertyInfo.setName(PARAMETER_NAME_ADD1);
            propertyInfo.setValue(params[1]);
            propertyInfo.setType(int.class);
            soapObject.addProperty(propertyInfo);

            // FORMA SEMPLIFICATA: aggiunta di un parametro con nome PARAMETER_NAME_ADD2 di tipo base
            soapObject.addProperty(PARAMETER_NAME_ADD2, params[2]);

            Log.i("com.example.samplews", "Request Value -> " + soapObject.toString());
            // creazione della busta SOAP da inviare
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            // inserimento nella busta dell'oggetto SOAP
            envelope.setOutputSoapObject(soapObject);

            // creazione della connessione con protocollo HTTP al web service
            HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

            try {
                // richiesta di esecuzione dell'operazione SOAP_ACTION ssui dati contenuti nella busta SOAP
                httpTransportSE.call(SOAP_ACTION, envelope);

                // ricezione della risposta
                SoapPrimitive soapPrimitive = (SoapPrimitive) envelope.getResponse();

                // FORMA SEMPLIFICATA: estrazione dalla risposta del contenuto
                str = soapPrimitive.toString();

                Log.i("com.example.samplews", "Risultato -------------- " + str);
                return str;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (params[0].equals(METHOD_NAME_SQRT)) {

            // impostazione nome esteso dell'operazione
            String SOAP_ACTION = NAMESPACE + METHOD_NAME_SQRT;

            // creazione dell'oggetto per la comunicazione con il web service
            // impostando il namespace e l'operazione da eseguire
            SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_NAME_SQRT);

            // preparazione delle proprietà/parametri dell'operazione
            PropertyInfo propertyInfo = new PropertyInfo();

            // FORMA ESTESA: aggiunta di un parametro con nome PARAMETER_NAME_ADD1 di tipo int
            propertyInfo.setName(PARAMETER_NAME_SQRT1);
            propertyInfo.setValue(params[1]);
            propertyInfo.setType(Double.class);
            soapObject.addProperty(propertyInfo);

            Log.i("com.example.samplews", "Request Value -> " + soapObject.toString());
            // creazione della busta SOAP da inviare
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            // inserimento nella busta dell'oggetto SOAP
            envelope.setOutputSoapObject(soapObject);

            // creazione della connessione con protocollo HTTP al web service
            HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

            try {
                // richiesta di esecuzione dell'operazione SOAP_ACTION ssui dati contenuti nella busta SOAP
                httpTransportSE.call(SOAP_ACTION, envelope);

                // ricezione della risposta
                SoapPrimitive soapPrimitive = (SoapPrimitive) envelope.getResponse();

                // FORMA SEMPLIFICATA: estrazione dalla risposta del contenuto
                str = soapPrimitive.toString();

                Log.i("com.example.samplews", "Risultato -------------- " + str);
                return str;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (params[0].equals(METHOD_NAME_LIST)) {
            String SOAP_ACTION = NAMESPACE + METHOD_NAME_LIST;

            SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_NAME_LIST);

            soapObject.addProperty(PARAMETER_NAME_LIST1, PARAMETER_VALUE_LIST1);

            Log.i("com.example.samplews", "Request Value -> " + soapObject.toString());
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

            // http://stackoverflow.com/questions/11029205/ksoap2-android-receive-array-of-objects
            try {
                httpTransportSE.call(SOAP_ACTION, envelope);
                SoapObject primitive = (SoapObject) envelope.bodyIn;
                for (int i = 0; i < primitive.getPropertyCount(); i++) {
                    str = (String) primitive.getProperty(i);

                    Log.i("com.example.samplews", "ForLoop -------------- " + str);
                    arraylist.add(str);
                }
                return "lista";

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    // http://stackoverflow.com/questions/12575068/how-to-get-the-result-of-onpostexecute-to-main-activity-because-asynctask-is-a

    @Override
    protected void onPostExecute(String esito) {
        super.onPostExecute(esito);

        // richiamato al termine della chiamata al web service
        // il parametro esito è il valore ritornato da doInBackground()

        if (esito != null) {
            if (esito.equals("lista")) {
                for (int i = 0; i < arraylist.size(); i++) {
                    MainActivity.addItemToSpinner(arraylist.get(i));
                }
                MainActivity.notifyDataSetChanged();
            } else
                textViewRisultato.setText("Risultato " + esito);
        }
    }

    public void setTextViewRisultato(TextView tv) {
        textViewRisultato = tv;
    }

}