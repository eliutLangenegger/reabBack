package cl.gendarmeria.reabback.resources;

import cl.gendarmeria.reabback.security.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author Christopher Langenegger
 * @version 1.0
 * @since 06-07-2022
 **/

@RestController
@RequestMapping("/pdfGenerator")
@CrossOrigin("*")
@RequiredArgsConstructor
public class PdfGenerator {

    private static final PDDocument PD_DOCUMENT_FOR_IMAGES = new PDDocument();
    private final UsuarioService usuarioService;

    DateFormat horaFormat = new SimpleDateFormat("HH:mm");
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
/*
    @PostMapping("/getPDf")
    public ResponseEntity<?> getPdf(HttpServletResponse response, @RequestBody RecordSendDto dto) throws Exception{
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);

        Usuario usuario = usuarioService.getByNombreUsuario(dto.getUser()).get();

        document.addPage(page);
        try(PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true)) {
            float x = 50f;
            float y = page.getMediaBox().getUpperRightY() - 40f;

            //Titulo
            contentStream.beginText();
            contentStream.newLineAtOffset(x+170, y-75);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
            contentStream.showText("INGRESO ABOGADO");
            contentStream.endText();

            //Logo GENDARMERIA
            PDImageXObject pdImageXObject1 = PDImageXObject.createFromFile("E:/REAB 1/reabBack/escudo.png", document);
            contentStream.drawImage(pdImageXObject1, x, y-75, 80, 80);

            Table antecedentes = Table.builder()
                    .addColumnsOfWidth(200, 322)
                    .fontSize(12)
                    .addRow(Row.builder()
                            .add(TextCell.builder().text("ANTECEDENTES DEL ABOGADO")
                                    .font(PDType1Font.HELVETICA_BOLD).fontSize(14).colSpan(2)
                                    .borderWidth(1).horizontalAlignment(CENTER).build())
                            .build())
                    .addRow(Row.builder()
                            .add(ParagraphCell.builder()
                                    .borderWidth(1)
                                    .verticalAlignment(MIDDLE)
                                    .paragraph(ParagraphCell.Paragraph.builder()
                                            .append(Markup.builder()
                                                    .markup(
                                                            "{color:#000000}*RUT:* "+dto.getLawyer().toUpperCase(Locale.ROOT)
                                                    ).font(Markup.MarkupSupportedFont.HELVETICA)
                                                    .build())
                                            .build())
                                    .build())
                            .add(ParagraphCell.builder()
                                    .borderWidth(1)
                                    .verticalAlignment(MIDDLE)
                                    .paragraph(ParagraphCell.Paragraph.builder()
                                            .append(Markup.builder()
                                                    .markup(
                                                            "{color:#000000}*NOMBRE:* "+dto.getName().toUpperCase(Locale.ROOT)
                                                    ).font(Markup.MarkupSupportedFont.HELVETICA)
                                                    .build())
                                            .build())
                                    .build())
                            .build())
                    .addRow(Row.builder()
                            .add(ParagraphCell.builder()
                                    .borderWidth(1)
                                    .verticalAlignment(MIDDLE)
                                    .paragraph(ParagraphCell.Paragraph.builder()
                                            .append(Markup.builder()
                                                    .markup(
                                                            "{color:#000000}*FECHA:* "+dateFormat.format(new Date())+" "+horaFormat.format(new Date())
                                                    ).font(Markup.MarkupSupportedFont.HELVETICA)
                                                    .build())
                                            .build())
                                    .build())
                            .add(ParagraphCell.builder()
                                    .borderWidth(1)
                                    .verticalAlignment(MIDDLE)
                                    .paragraph(ParagraphCell.Paragraph.builder()
                                            .append(Markup.builder()
                                                    .markup(
                                                            "{color:#000000}*UNIDAD:* "+dto.getUnit()
                                                    ).font(Markup.MarkupSupportedFont.HELVETICA)
                                                    .build())
                                            .build())
                                    .build())
                            .build())
                    .build();

            Table.TableBuilder internalsTable = Table.builder()
                    .addColumnsOfWidth(100)
                    .addColumnsOfWidth(297)
                    .addColumnsOfWidth(125);

            internalsTable.addRow(Row.builder()
                    .add(TextCell.builder().text("RUT")
                            .font(PDType1Font.HELVETICA_BOLD)
                            .borderWidth(1).horizontalAlignment(HorizontalAlignment.CENTER).build())
                    .add(TextCell.builder().text("NOMBRE")
                            .font(PDType1Font.HELVETICA_BOLD)
                            .borderWidth(1).horizontalAlignment(HorizontalAlignment.CENTER).build())
                    .add(TextCell.builder().text("DEPENDENCIA")
                            .font(PDType1Font.HELVETICA_BOLD)
                            .borderWidth(1).horizontalAlignment(HorizontalAlignment.CENTER).build())
                    .build());


            for (InternalDto i: dto.getInternals()) {
                byte[] decodedString = Base64.getDecoder().decode(new String(i.getPhoto()).getBytes("UTF-8"));
                internalsTable.addRow(
                        Row.builder()
                                .add(TextCell.builder()
                                        .text(i.getRun())
                                        .borderWidth(1F)
                                        .build())
                                .add(TextCell.builder()
                                        .text(i.getName()+" "+i.getSur())
                                        .horizontalAlignment(HorizontalAlignment.CENTER)
                                        .borderWidth(1F)
                                        .build())
                                .add(TextCell.builder()
                                        .text(i.getDepen())
                                        .horizontalAlignment(HorizontalAlignment.CENTER)
                                        .borderWidth(1F)
                                        .build())
                                .add(
                                        ImageCell.builder()
                                                .verticalAlignment(MIDDLE)
                                                .horizontalAlignment(CENTER)
                                                .borderWidth(1)
                                                .image(PDImageXObject.createFromByteArray(PD_DOCUMENT_FOR_IMAGES, decodedString, "sample"))
                                                .scale(0.4f).build()
                                )
                        .build());
            }

            Table firmaTable = Table.builder()
                    .addColumnsOfWidth(522)
                    .fontSize(12)
                    .addRow(Row.builder()
                            .add(TextCell.builder().text(usuario.getNombres().toUpperCase(Locale.ROOT)+" "+usuario.getApellidos().toUpperCase(Locale.ROOT))
                                    .font(PDType1Font.HELVETICA_BOLD).fontSize(14).verticalAlignment(MIDDLE)
                                    .horizontalAlignment(CENTER).build())
                            .build())
                    .addRow(Row.builder()
                            .add(TextCell.builder().text(usuario.getGrado().toUpperCase(Locale.ROOT))
                                    .font(PDType1Font.HELVETICA_BOLD).fontSize(14).verticalAlignment(MIDDLE)
                                    .horizontalAlignment(CENTER).build())
                            .build())
                    .addRow(Row.builder()
                            .add(TextCell.builder().text(usuario.getCargo().toUpperCase(Locale.ROOT))
                                    .font(PDType1Font.HELVETICA_BOLD).fontSize(14).verticalAlignment(MIDDLE)
                                    .horizontalAlignment(CENTER).build())
                            .build())
                    .build();

            TableDrawer docDrawer = TableDrawer.builder()
                    .contentStream(contentStream)
                    .startX(x)
                    .startY(y-95f)
                    .page(page)
                    .table(antecedentes)
                    .build();
            docDrawer.draw();

            TableDrawer internalsTableDrawer = TableDrawer.builder()
                    .contentStream(contentStream)
                    .startX(x)
                    .startY(y-175f)
                    .page(page)
                    .table(internalsTable.build())
                    .build();
            internalsTableDrawer.draw();

            TableDrawer firmaDrawer = TableDrawer.builder()
                    .contentStream(contentStream)
                    .startX(x)
                    .startY(y-650f)
                    .page(page)
                    .table(firmaTable)
                    .build();
            firmaDrawer.draw();
        }

        //Descarga
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try{
            document.save(byteArrayOutputStream);
            document.close();
        }catch (Exception e){
            throw new Exception(e);
        }
        response.setContentType("application/pdf");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,"attachment;File-Name= NombreArchivo");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(byteArrayOutputStream.toByteArray());
        outputStream.close();
        return new ResponseEntity<>(HttpStatus.OK);
    }*/
}
