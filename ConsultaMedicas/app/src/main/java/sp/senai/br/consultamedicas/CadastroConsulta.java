package sp.senai.br.consultamedicas;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CadastroConsulta extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    Intent intent;
    int idMedico;
    String nomeMedico;

    TextView consultaNomeMedico;
    TextView txtNomeDia;
    Button btnAbrirCalendario;



    Context ctx = this;

    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat dt_format = new SimpleDateFormat("dd-MM-yyyy");

    int idSemana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_consulta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        consultaNomeMedico = findViewById(R.id.consultaNomeMedico);
        txtNomeDia = findViewById(R.id.txtNomeDia);
        btnAbrirCalendario = findViewById(R.id.btnAbrirCalendario);

        intent = getIntent();
        idMedico = intent.getIntExtra("idMedico", 0);
        nomeMedico = intent.getStringExtra("nomeMedico");

        consultaNomeMedico.setText(nomeMedico);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(ctx, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        btnAbrirCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
        /*Pegando o que foi selecionado do calendário*/
        calendar.set(Calendar.YEAR, ano);
        calendar.set(Calendar.MONTH, mes);
        calendar.set(Calendar.DAY_OF_MONTH, dia);

        Date dataSelecionada = calendar.getTime();
        String data = dt_format.format(dataSelecionada);
        btnAbrirCalendario.setText(data);
        idSemana = calendar.get(Calendar.DAY_OF_WEEK);

        Log.d("idSemana", String.valueOf(idSemana));

        if(idSemana == 1){
            txtNomeDia.setText("Domingo");
        }else if(idSemana == 2){
            txtNomeDia.setText("Segunda - Feira");
        }else if(idSemana == 3){
            txtNomeDia.setText("Terça- Feira");
        }else if(idSemana == 4){
            txtNomeDia.setText("Quarta - Feira");
        }else if(idSemana == 5){
            txtNomeDia.setText("Quinta - Feira");
        }else if(idSemana == 6){
            txtNomeDia.setText("Sexta- Feira");
        }else if(idSemana == 7){
            txtNomeDia.setText("Sábado");
        }

    }

    private class HorariosDisponiveisAPI extends AsyncTask<Void, Void, String>{

        String url = "http://10.0.2.2/INF4T20181/ConsultasMedicasAPI/buscarHorariosDisponiveis.php";

        @Override
        protected String doInBackground(Void... voids) {
            String json = "";

            json = HttpConnection.get(url);

            return json;
        }

        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);


        }
    }

}
