package pages.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader implements IExternalReader {
    private final String PATH_SEPARATOR = "/";
    private static final int MAIN_BY_NUMBER_SHEET = 0;

    private String filename;
    private String path;

    public ExcelReader(String filename) {
        this.filename = filename;
        this.path = this.getClass().getResource(PATH_SEPARATOR + filename).getPath().substring(1);
    }

    public String getFilename() {
        return this.filename;
    }

    public String getPath() {
        return this.path;
    }

    public List<List<String>> getAllCells() {
        return getAllCells(path);
    }

    public List<List<String>> getAllCells(String path) {
        List<List<String>> allCells = new ArrayList<List<String>>();
        InputStream inputStream = null;
        DataFormatter formatter = new DataFormatter();
        XSSFWorkbook workBook = null;
        Sheet sheet = null;
        try {

            inputStream = new FileInputStream(path);

            workBook = new XSSFWorkbook(inputStream);

            sheet = workBook.getSheetAt(MAIN_BY_NUMBER_SHEET);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(String.format(FILE_NOT_FOUND_EXCEPTION, path));
        } catch (IOException e) {
            throw new RuntimeException(String.format(FILE_NOT_READ_EXCEPTION, path));
        }
        Iterator<Row> rowsIterator = sheet.iterator();
        while (rowsIterator.hasNext()) {
            Row row = rowsIterator.next();
            Iterator<Cell> cellsIterator = row.iterator();
            List<String> allRowCells = new ArrayList<String>();
            while (cellsIterator.hasNext()) {
                String cell = formatter.formatCellValue(cellsIterator.next());
                allRowCells.add(cell);
            }
            allCells.add(allRowCells);
        }
        if (workBook != null) {
            try {
                workBook.close();
            } catch (IOException e) {
                throw new RuntimeException(String.format(FILE_NOT_CLOSE_EXCEPTION, path), e);
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(String.format(FILE_NOT_CLOSE_EXCEPTION, path), e);
            }
        }
        return allCells;
    }

}