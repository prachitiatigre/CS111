/*************************************************************************
 *  Compilation:  javac ArtCollage.java
 *  Execution:    java ArtCollage
 *
 *  @author : Prachiti Atigre
 *
 *************************************************************************/

import java.awt.Color;

public class ArtCollage {

    // The orginal picture
    private Picture original;

    // The collage picture
    private Picture collage;

    // The collage Picture consists of collageDimension X collageDimension tiles
    private int collageDimension;

    // A tile consists of tileDimension X tileDimension pixels
    private int tileDimension;
    
    /*
     * One-argument Constructor
     * 1. set default values of collageDimension to 4 and tileDimension to 100
     * 2. initializes original with the filename image
     * 3. initializes collage as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collage to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */

    public ArtCollage (String filename) {
        this.collageDimension = 4; 
        this.tileDimension = 100;
        this.original = new Picture(filename);
        this.collage = new Picture(tileDimension * collageDimension, tileDimension * collageDimension);

        int k = tileDimension * collageDimension; 
        for(int colx = 0; colx < k; colx++) {
            for(int rowx = 0; rowx < k; rowx++) {
                int x = colx * original.width() / k;
                int y = rowx * original.height() / k; 
                Color color = original.get(x,y);
                collage.set(colx,rowx,color);
            }
        }
    }

    /*
     * Three-arguments Constructor
     * 1. set default values of collageDimension to cd and tileDimension to td
     * 2. initializes original with the filename image
     * 3. initializes collage as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collage to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public ArtCollage (String filename, int td, int cd) {
        original = new Picture(filename);
        collageDimension = cd;
        tileDimension = td;
        collage = new Picture((td * cd), (td * cd));

        for (int tcol = 0; tcol < (td * cd); tcol++) {
            for (int trow = 0; trow < (td * cd); trow++) {
                int scol = tcol * original.width() / (td * cd);
                int srow = trow * original.height() / (td * cd);

                Color color = original.get(scol, srow);
                collage.set(tcol, trow, color);
            }
        }   
    }

    /*
     * Returns the collageDimension instance variable
     *
     * @return collageDimension
     */
    public int getCollageDimension() {
        return collageDimension;
    }

    /*
     * Returns the tileDimension instance variable
     *
     * @return tileDimension
     */
    public int getTileDimension() {
        return tileDimension;
    }

    /*
     * Returns original instance variable
     *
     * @return original
     */
    public Picture getOriginalPicture() {
        return original;
    }

    /*
     * Returns collage instance variable
     *
     * @return collage
     */
    public Picture getCollagePicture() {
        return this.collage;
    }
    
    /*
     * Display the original image
     * Assumes that original has been initialized
     */
    public void showOriginalPicture() {
        original.show();
    }

    /*
     * Display the collage image
     * Assumes that collage has been initialized
     */
    public void showCollagePicture() {
        collage.show();
    }

    /*
     * Replaces the tile at collageCol,collageRow with the image from filename
     * Tile (0,0) is the upper leftmost tile
     *
     * @param filename image to replace tile
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void replaceTile (String filename,  int collageCol, int collageRow) {
        Picture boosted  = new Picture(filename); //original
        Picture image = new Picture(tileDimension, tileDimension); //target

        // i = col
        // j = row

        //scaling
        for (int tcol = 0; tcol < tileDimension; tcol++) {
            for (int trow = 0; trow < tileDimension; trow++) {
                int scol = tcol * boosted.width() / (tileDimension);
                int srow = trow * boosted.height() / (tileDimension);

                Color color = boosted.get(scol, srow);
                image.set(tcol, trow, color);
            }
        }

        int yy = (collageCol) * (tileDimension);
        int zz = (collageRow) * (tileDimension);

        for (int icol = yy; icol < yy+tileDimension; icol++) {
            for (int irow = zz; irow < zz+tileDimension; irow++) {
                Color iColor = image.get(icol-yy, irow-zz);
                collage.set(icol ,  irow, iColor);
            }
        }
    }
    
    /*
     * Makes a collage of tiles from original Picture
     * original will have collageDimension x collageDimension tiles, each tile
     * has tileDimension X tileDimension pixels
     */
    public void makeCollage() {
        Picture image = new Picture(tileDimension, tileDimension);

        int k = tileDimension; 

        for(int colx = 0; colx < tileDimension; colx++) {
            for(int rowx = 0; rowx < tileDimension; rowx++) {
                int x = colx * original.width() / k;
                int y = rowx * original.height() / k;
                Color color = original.get(x,y);
                image.set(colx, rowx, color); 
            }
        }

        for (int imageHeight = 0; imageHeight < collage.height(); imageHeight = imageHeight + tileDimension) {
            for (int imageRow = 0; imageRow < collage.width(); imageRow = imageRow + tileDimension) {
                for(int rowx = 0; rowx < tileDimension; rowx++) {
                    for(int colx = 0; colx < tileDimension; colx++) {
                        Color color = image.get(rowx, colx);
                        collage.set(rowx + imageRow, colx + imageHeight, color);
                    }
                }
            }
        }
    }

    /*
     * Colorizes the tile at (collageCol, collageRow) with component 
     * (see Week 9 slides, the code for color separation is at the 
     *  book's website)
     *
     * @param component is either red, blue or green
     * @param collageCol tile column
     * @param collageRow tile row
     */

    public void colorizeTile (String component, int collageCol, int collageRow) {
        int col = tileDimension * collageCol;
        int row = tileDimension * collageRow;
        
        for (int i = col; i < (col + tileDimension); i++) {
            for(int j = row; j < (row + tileDimension); j++) {
                Color color = collage.get(i, j);

                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                if(component.equals("red"))
                    collage.set(i, j, new Color(r, 0, 0));
                if(component.equals("green"))
                    collage.set(i, j, new Color(0, g, 0));
                if(component.equals("blue"))
                    collage.set(i, j, new Color(0, 0, b));
            }
        }
    }

    /*
     * Greyscale tile at (collageCol, collageRow)
     * (see Week 9 slides, the code for luminance is at the book's website)
     *
     * @param collageCol tile column
     * @param collageRow tile row
     */


    public void greyscaleTile (int collageCol, int collageRow) {
        int row = collageRow * tileDimension; 
        int col= collageCol * tileDimension; 

        for(int i = row; i < row + tileDimension; i++) {
            for(int j = col; j < col + tileDimension; j++) {
                Color color = collage.get(j,i);
                Color gray = Luminance.toGray(color); 
                collage.set(j, i, gray); 
            }
        }
    }

    // Test client 
    public static void main (String[] args) { 
        ArtCollage art = new ArtCollage(args[0], 200, 4);
        art.makeCollage();

        // Replace tile at col 1, row 1 with args[1] image
        art.greyscaleTile(1,3);
        art.showCollagePicture();
    }
}






