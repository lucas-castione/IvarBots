package com.example.IvarBots.controller;

import com.example.IvarBots.auxiliar.Arquivo;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/files")
@CrossOrigin
public class FileController {


    @PostMapping("/{nomeArquivo}")
    public ResponseEntity gravaArquivo(@RequestBody Object tete, @PathVariable String nomeArquivo){
        String fileName = ".\\arquivos\\"+nomeArquivo;
        String encoding = "UTF-8";
        try{
            PrintWriter writer = new PrintWriter(fileName, encoding);
            writer.println(tete);
            writer.close();
            return  ResponseEntity.status(200).body(tete);
        }
        catch (IOException e){
            return  ResponseEntity.status(200).body("ouve um erro");
        }

    }

    @GetMapping("/{nomeArq}")
    public ResponseEntity download(@PathVariable String nomeArq){
        String filename = ".\\arquivos\\"+nomeArq;

        try {
            File file = new File(filename);
            Path path = Paths.get(file.getAbsolutePath());
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
            String contentType = "application/octet-stream";
            String headerValue = "attachment; filename=" + nomeArq;
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listaArquivos")
    public ResponseEntity listaArquivos(){
        DateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy hh:mm a");
        String diretorio = ".\\arquivos";

        try{
            File file = new File(diretorio);
            File listaArquivo[] = file.listFiles();
            List<Arquivo> listaArquivos = new ArrayList();

            for (File f: listaArquivo){
                Arquivo arquivo = new Arquivo();
                arquivo.setNome(f.getName());
                arquivo.setData(sdf.format(f.lastModified()));
                listaArquivos.add(arquivo);
            }
            return ResponseEntity.status(200).body(listaArquivos);


        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }
}
