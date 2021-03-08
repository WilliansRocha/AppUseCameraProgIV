package br.com.progiv.appusecamera;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button btnFoto;
    Button btnGravar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Checar se tenho permissao para usar a camera
        if(ActivityCompat.checkSelfPermission(context this, Manifest.permission.Camera) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity this, new String[]{
                    Manifest.permission.CAMERA}, requestCode 0);
        }

        //CAST - Parse de componentes em tela para código
        imageView = (ImageView)findViewById(R.id.imageView);
        btnFoto = (Button) findViewById(R.id.btnFoto);
        btnGravar = (Button) findViewById(R.id.btnGravar);

        btnFoto.setOnClickListener(new View.OnclickListener(){
            @Override
            public void onclick(View v){
                tirarFoto();
            }
        });
}
    public void tiraFoto(){
    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    starActivityForResult(intent, requestCodew 1);
    }
    public void gravarVideo(){
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        starActivityForResult(intent, requestCode 2);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCOde, @Nullable Intent data){
      /*  if(requestCode == 1 && resultCode == RESULT_OK){
            Bundle = data.getExtras();
            Bitmap imagem = (Bitmap)bundle.get("data");
            imageView.setImageBitmap(imagem);
        }*/
        if(resultCode == RESULT_OK){
            switch(requestCode){
                case 1:
                    Bundle bundle = data.getExtras();
                    Bitmap imagem =(Bitmap)bundle.get("data");
                    imageView.setImageBitmap(imagem);
                    break;
                case 2:
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    VideoView videoView = new VideoView(this);
                    videoView.setVideoURI(data.getData());
                    videoView.start();
                    builder.setView(videoView).show();}
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}