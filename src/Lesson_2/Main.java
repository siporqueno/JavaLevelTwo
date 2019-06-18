package Lesson_2;

public class Main {
    private static String[][] testArrayOne = {
            {"1", "1", "1", "2"},
            {"1", "???", "1", "1"},
            {"1", "1", "1"},
            {"1", "1", "1", "1",}
    };

    private static final int ROWS_NO_TARGET = 4;
    private static final int COLUMNS_NO_TARGET = 4;

    public static void main(String[] args) {
        try {
            System.out.println(new Main().sumOfStrArr(testArrayOne));
        } catch (MyArraySizeExceptionRowsNo eRowsNo) {
            System.out.printf("Количество строк %d, а должно быть %d\n", eRowsNo.getTotalRowsNo(), ROWS_NO_TARGET);
            eRowsNo.printStackTrace();
        } catch (MyArraySizeExceptionColumnsNo eColumnsNo) {
            System.out.printf("Строка номер %d состоит из %d столбцов, а не из %d\n", eColumnsNo.getRow(), eColumnsNo.getTotalColumnsNo(), COLUMNS_NO_TARGET);
            eColumnsNo.printStackTrace();
        } catch (MyArrayDataException eData) {
            System.out.printf("В ячейке %d %d нет числа\n", eData.getRowNo(), eData.getColNo());
            eData.printStackTrace();
        }

    }

    int sumOfStrArr(String[][] arrTwoDim) throws MyArraySizeExceptionRowsNo, MyArraySizeExceptionColumnsNo, MyArrayDataException {
        int sum = 0;
        if (arrTwoDim.length != ROWS_NO_TARGET) throw new MyArraySizeExceptionRowsNo("Количество строк неверное!!!", arrTwoDim.length);

        for (int i = 0; i < arrTwoDim.length; i++) {
            if (arrTwoDim[i].length != COLUMNS_NO_TARGET)
                throw new MyArraySizeExceptionColumnsNo("Количество столбцов в строке неверное!!!", i, arrTwoDim[i].length);
            for (int j = 0; j < arrTwoDim[i].length; j++) {
                try {
                    sum += Integer.parseInt(arrTwoDim[i][j]);

                } catch (NumberFormatException e) {
                    System.out.println("Исключение поймали, обработали и бросили дальше");
                    throw new MyArrayDataException("В этой ячейке строка, в которой нет числа!!!", i, j);
                }
            }
        }

        return sum;
    }

}

class MyArraySizeExceptionRowsNo extends ArrayIndexOutOfBoundsException {
    private int totalRowsNo;

    public MyArraySizeExceptionRowsNo(String s, int totalRowsNo) {
        super(s);
        this.totalRowsNo = totalRowsNo;
    }

    public int getTotalRowsNo() {
        return totalRowsNo;
    }
}

class MyArraySizeExceptionColumnsNo extends ArrayIndexOutOfBoundsException {
    private int row, totalColumnsNo;

    public MyArraySizeExceptionColumnsNo(String s, int row, int totalColumnsNo) {
        super(s);
        this.row = row;
        this.totalColumnsNo = totalColumnsNo;
    }

    public int getRow() {
        return row;
    }

    public int getTotalColumnsNo() {
        return totalColumnsNo;
    }
}

class MyArrayDataException extends NumberFormatException {
    private int rowNo, colNo;

    public MyArrayDataException(String s, int rowNo, int colNo) {
        super(s);
        this.rowNo = rowNo;
        this.colNo = colNo;
    }

    public int getRowNo() {
        return rowNo;
    }

    public int getColNo() {
        return colNo;
    }
}
