package com.example.misnotasv1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.misnotasv1.R;

import com.example.misnotasv1.adapters.NotesRecyclerViewAdapter;
import com.example.misnotasv1.models.Note;
import com.example.misnotasv1.utils.SpacingItemDecorator;

import java.util.ArrayList;

public class NoteListActivity extends AppCompatActivity implements NotesRecyclerViewAdapter.OnNoteListener {

    private  static final String TAG = "NoteListActivity";
    private RecyclerView mRecyclerView;
    private ArrayList<Note> mNotes = new ArrayList<>();
    private NotesRecyclerViewAdapter mNotesRecyclerViewAdapter;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        mRecyclerView = findViewById(R.id.recyclerView);
        initRecyclerView();
        insertarDatosDePrueba();
        setSupportActionBar((Toolbar) findViewById(R.id.toolbarNote));
        //      Note note = new Note("Nota 1","Esta es una primera nota","02 de Noviembre de 2020");
        //      Log.d(TAG, "onCreate" + note.toString());
    }
    public void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(20);
        mRecyclerView.addItemDecoration(itemDecorator);
        mNotesRecyclerViewAdapter= new NotesRecyclerViewAdapter(mNotes, this);
        mRecyclerView.setAdapter(mNotesRecyclerViewAdapter);
    }

    public void insertarDatosDePrueba(){
        for(int i = 0 ; i < 50 ; i++){
            Note note = new Note();
            note.setTitle("titulo"+ i);
            note.setContent("texto de la nota"+i);
            note.setTimestamp("Mayo de 2020");
            mNotes.add(note);
        }
        mNotesRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNoteClick(int position){
        Log.d(TAG, "onNoteClick: Usted hizo click en el elemento" + position + "de la lista");
        Intent intent = new Intent(NoteListActivity.this, NoteActivity.class);
        intent.putExtra("selected_note", mNotes.get(position));
        startActivity(intent);
    }



}
