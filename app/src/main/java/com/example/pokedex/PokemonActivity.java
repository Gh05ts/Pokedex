package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PokemonActivity extends AppCompatActivity {
    private TextView nameTextView;
    private TextView numberTextView;
    private TextView type1TextView;
    private TextView type2TextView;
    private ImageView pokemonImageView;
    String url;
    private RequestQueue requestQueue;
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

//        String name = getIntent().getStringExtra("name");
//        int number = getIntent().getIntExtra("number", 0);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        url = getIntent().getStringExtra("url");

        nameTextView = findViewById(R.id.pokemon_name);
        numberTextView = findViewById(R.id.pokemon_number);
        type1TextView = findViewById(R.id.pokemon_type1);
        type2TextView = findViewById(R.id.pokemon_type2);
        pokemonImageView = findViewById(R.id.pokeMon_image);
        load();

        ImageView backButton = (ImageView) this.findViewById(R.id.back_btton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView soundButton = (ImageView) this.findViewById(R.id.soundbtn);
        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = "a"+num;
//                Log.i("mytag", "inside" + s);
                Resources res = getApplicationContext().getResources();
                int soundID = res.getIdentifier(s, "raw", getApplicationContext().getPackageName());
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), soundID);
//                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.a1);
                mediaPlayer.start(); // no need to call prepare(); create() does that for you
            }
        });

//        nameTextView.setText(name);
//        numberTextView.setText(String.format("#%03d", number));
    }

    public void load() {
        type1TextView.setText("");
        type2TextView.setText("");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String text1 ="";
                String text2 = "";
                try {
//                    JSONObject spriteurl = response.getJSONObject("sprites");
//                    Picasso.get().load(spriteurl.getString("front_shiny"))
                    int idGetResource = response.getInt("id");
                    num = idGetResource;
                    Picasso.get().load("https://veekun.com/dex/media/pokemon/sugimori/" + idGetResource + ".png")
                            .resize(600, 600)
                            .centerCrop()
                            .into(pokemonImageView);
                    String namePlaceHolder = "Waiting";
                    namePlaceHolder = response.getString("name").substring(0, 1).toUpperCase()
                            + response.getString("name").substring(1);
                    nameTextView.setText(namePlaceHolder);
                    String idPlaceHolder = String.format("#%03d", idGetResource);
                    numberTextView.setText(idPlaceHolder);
                    JSONArray typeEntries = response.getJSONArray("types");
                    for(int i = 0; i < typeEntries.length(); i++) {
                        JSONObject typeEntry = typeEntries.getJSONObject(i);
                        int slot = typeEntry.getInt("slot");
                        String type = typeEntry.getJSONObject("type").getString("name");

                        if (slot == 1) {
                            type1TextView.setText(type);
                            text1 = type;
                        }
                        else if (slot == 2) {
                            type2TextView.setText(type);
                            text2 = type;
                        }
                    }

                    switch (text1) {
                        case "normal":
                            type1TextView.setBackgroundResource(R.color.normal);
                            break;
                        case "fire":
                            type1TextView.setBackgroundResource(R.color.fire);
                            break;
                        case "fighting":
                            type1TextView.setBackgroundResource(R.color.fighting);
                            break;
                        case "water":
                            type1TextView.setBackgroundResource(R.color.water);
                            break;
                        case "flying":
                            type1TextView.setBackgroundResource(R.color.flying);
                            break;
                        case "grass":
                            type1TextView.setBackgroundResource(R.color.grass);
                            break;
                        case "poison":
                            type1TextView.setBackgroundResource(R.color.poison);
                            break;
                        case "electric":
                            type1TextView.setBackgroundResource(R.color.electric);
                            break;
                        case "ground":
                            type1TextView.setBackgroundResource(R.color.ground);
                            break;
                        case "psychic":
                            type1TextView.setBackgroundResource(R.color.psychic);
                            break;
                        case "rock":
                            type1TextView.setBackgroundResource(R.color.rock);
                            break;
                        case "ice":
                            type1TextView.setBackgroundResource(R.color.ice);
                            break;
                        case "bug":
                            type1TextView.setBackgroundResource(R.color.bug);
                            break;
                        case "dragon":
                            type1TextView.setBackgroundResource(R.color.dragon);
                            break;
                        case "ghost":
                            type1TextView.setBackgroundResource(R.color.ghost);
                            break;
                        case "dark":
                            type1TextView.setBackgroundResource(R.color.dark);
                            break;
                        case "steel":
                            type1TextView.setBackgroundResource(R.color.steel);
                            break;
                        case "fairy":
                            type1TextView.setBackgroundResource(R.color.fairy);
                            break;
                        default:
                    }
                    if(text2 != "") {
                        switch (text2) {
                            case "normal":
                                type2TextView.setBackgroundResource(R.color.normal);
                                break;
                            case "fire":
                                type2TextView.setBackgroundResource(R.color.fire);
                                break;
                            case "fighting":
                                type2TextView.setBackgroundResource(R.color.fighting);
                                break;
                            case "water":
                                type2TextView.setBackgroundResource(R.color.water);
                                break;
                            case "flying":
                                type2TextView.setBackgroundResource(R.color.flying);
                                break;
                            case "grass":
                                type2TextView.setBackgroundResource(R.color.grass);
                                break;
                            case "poison":
                                type2TextView.setBackgroundResource(R.color.poison);
                                break;
                            case "electric":
                                type2TextView.setBackgroundResource(R.color.electric);
                                break;
                            case "ground":
                                type2TextView.setBackgroundResource(R.color.ground);
                                break;
                            case "psychic":
                                type2TextView.setBackgroundResource(R.color.psychic);
                                break;
                            case "rock":
                                type2TextView.setBackgroundResource(R.color.rock);
                                break;
                            case "ice":
                                type2TextView.setBackgroundResource(R.color.ice);
                                break;
                            case "bug":
                                type2TextView.setBackgroundResource(R.color.bug);
                                break;
                            case "dragon":
                                type2TextView.setBackgroundResource(R.color.dragon);
                                break;
                            case "ghost":
                                type2TextView.setBackgroundResource(R.color.ghost);
                                break;
                            case "dark":
                                type2TextView.setBackgroundResource(R.color.dark);
                                break;
                            case "steel":
                                type2TextView.setBackgroundResource(R.color.steel);
                                break;
                            case "fairy":
                                type2TextView.setBackgroundResource(R.color.fairy);
                                break;
                            default:
                        }
                    }
                }
                catch (JSONException e) {
                    Log.e("CS50", "Pokemon JSON error", e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("CS50", "Pokemon details error");
            }
        });

        requestQueue.add(request);
    }
}

// https://gist.githubusercontent.com/mrcsxsiq/b94dbe9ab67147b642baa9109ce16e44/raw/97811a5df2df7304b5bc4fbb9ee018a0339b8a38/