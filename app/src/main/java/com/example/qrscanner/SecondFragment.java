package com.example.qrscanner;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class SecondFragment extends Fragment
{

    EditText qrValue;
    Button genButton, scanButton;
    ImageView qrImage;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        final int[] count = {0};
        qrValue = view.findViewById(R.id.qrInput);
        genButton = view.findViewById(R.id.generateButton);
        qrImage = view.findViewById(R.id.imageHolder);

        qrValue.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            }
        });

        genButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String data = qrValue.getText().toString();

                if(data.isEmpty())
                {
                    qrValue.setError("no Value Input");
                }
                else
                {
                    QRGEncoder qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, 500);

                    try
                    {
                        Bitmap qrBits = qrgEncoder.encodeAsBitmap();
                        qrImage.setBackgroundColor(Color.WHITE);
                        qrImage.setImageBitmap(qrBits);
                    }
                    catch (WriterException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });



        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }
}
