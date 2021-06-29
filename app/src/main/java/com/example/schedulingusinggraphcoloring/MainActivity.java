package com.example.schedulingusinggraphcoloring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
class Graph
{
    private int V;   // No. of vertices
    private LinkedList<Integer> adj[]; //Adjacency List

    //Constructor
    Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    //Function to add an edge into the graph
    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v); //Graph is undirected
    }

    // Assigns colors (starting from 0) to all vertices and
    // prints the assignment of colors
    int[] greedyColoring() {
        int result[] = new int[V];

        // Initialize all vertices as unassigned
        Arrays.fill(result, -1);

        // Assign the first color to first vertex
        result[0] = 0;

        // A temporary array to store the available colors. False
        // value of available[cr] would mean that the color cr is
        // assigned to one of its adjacent vertices
        boolean available[] = new boolean[V];

        // Initially, all colors are available
        Arrays.fill(available, true);

        // Assign colors to remaining V-1 vertices
        for (int u = 1; u < V; u++) {
            // Process all adjacent vertices and flag their colors
            // as unavailable
            Iterator<Integer> it = adj[u].iterator();
            while (it.hasNext()) {
                int i = it.next();
                if (result[i] != -1)
                    available[result[i]] = false;
            }

            // Find the first available color
            int cr;
            for (cr = 0; cr < V; cr++) {
                if (available[cr])
                    break;
            }

            result[u] = cr; // Assign the found color

            // Reset the values back to true for the next iteration
            Arrays.fill(available, true);
        }

        return result;
    }
}

    public class MainActivity extends AppCompatActivity {

    EditText subjects,nopfpairs,pairsarray;
    Button result,reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        subjects = findViewById(R.id.numberofsubjects);
        nopfpairs = findViewById(R.id.numberofpairs);
        pairsarray = findViewById(R.id.allpairs);
        result = findViewById(R.id.result);
        reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subjects.setText("");
                nopfpairs.setText("");
                pairsarray.setText("");
            }
        });
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(subjects.getText().toString().isEmpty() || nopfpairs.getText().toString().isEmpty() || pairsarray.getText().toString().isEmpty() )
                {
                    Toast.makeText(getApplicationContext(),"Please enter all the fields",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    int nsubjects=Integer.parseInt(subjects.getText().toString());
                    int npairs=Integer.parseInt(subjects.getText().toString());
                    ArrayList<Integer> pairsarr=new ArrayList<Integer>();
                    String tmp=pairsarray.getText().toString();
                    String tmp1="";
                    for(int i=0;i<tmp.length();i++)
                    {
                        if(tmp.charAt(i)!=' ')tmp1=tmp1+tmp.charAt(i);
                    }
                    Graph g1 = new Graph(nsubjects);
                    for(int i=0;i<tmp1.length();i+=2)
                    {
                        g1.addEdge(((tmp1.charAt(i)-'0')-1),((tmp1.charAt(i+1)-'0')-1));
                    }
                    int []arrans = g1.greedyColoring();
                    String ans="";
                    for(int i=0;i<arrans.length;i++)
                    {
                        Log.v("ans" ,String.valueOf(i+1)+" "+String.valueOf(arrans[i]+1));
                        ans=ans+"SLOT Assigned to "+ (i+1) +" is SLOT - "+ (arrans[i]+1) +"\n";
                    }
                    Log.v("ans",ans);
                    //for one activity to another
                    Intent intent = new Intent(MainActivity.this,AnswerActivity.class);
                    intent.putExtra("answerintent",ans);
                    startActivity(intent);

                }
            }
        });
    }
}