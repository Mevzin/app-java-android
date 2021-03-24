package com.example.covid19av2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Sobre extends AppCompatActivity {

    private TextView sobre;
    private Button voltarmenu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        sobre = findViewById(R.id.tvSobre);

        voltarmenu = findViewById(R.id.btVoltarMenu);

        voltarmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sobre.setText("             Dicas para se previnir :D     " +
                "\n"+
                "\n Proteja a si mesmo e as pessoas ao seu redor conhecendo os fatos e tomando as precauções apropriadas. Siga os conselhos fornecidos pela sua agência local de saúde pública.\n" +
                "Para evitar a propagação da COVID-19:\n" +
                "   \n*Lave suas mãos com frequência. Use sabão e água ou um gel à base de álcool.\n" +
                "   \n*Mantenha uma distância segura de qualquer pessoa que esteja tossindo ou espirrando.\n" +
                "   \n*Não toque nos olhos, no nariz ou na boca.\n" +
                "   \n*Quando tossir ou espirrar, cubra o nariz e a boca com o cotovelo dobrado ou um tecido.\n" +
                "   \n*Fique em casa se você se sentir indisposto.\n" +
                "   \n*Se você tiver febre, tosse e dificuldade para respirar, procure assistência médica. Ligue antes de sair.\n" +
                "   \n*Siga as instruções de sua autoridade de saúde local.\n" +
                "   \n*Evite ir desnecessariamente a clínicas ou hospitais para permitir que os sistemas de saúde operem com mais eficiência, protegendo você e as outras pessoas.");
    }
}
