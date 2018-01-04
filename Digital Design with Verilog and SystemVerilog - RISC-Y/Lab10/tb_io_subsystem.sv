`timescale 1ns / 1ns

module tb_io_subsystem( );
    reg CLK, DATA, PDR_EN, RST, PORT_EN, PORT_RD;
    reg [7:0] DATA2;
    
    reg [7:0] IO_IN;
    wire [7:0] IO, PORT_READ_DATA;
    
    wire P_DIR_REG2TRI; 
    wire [7:0] P_DATA_REG2TRI, TRI2TRI;
    
     // Port Direction Register (CLK, RST_N, EN, Input, Output
     portdirectionregister(CLK, RST, PDR_EN, DATA, P_DIR_REG2TRI);
        
     // Port Data Register CLK, EN, Input, Output
     Scalable_Register pdatareg(CLK, PORT_EN, DATA2, P_DATA_REG2TRI);
        
     // Tri-state buffer 1
     tribuf t1(P_DATA_REG2TRI, P_DIR_REG2TRI, TRI2TRI);
     // Tri-state buffer 2
     tribuf t2(TRI2TRI, PORT_RD, PORT_READ_DATA);
    
    assign IO = (P_DIR_REG2TRI && !PORT_RD) ? TRI2TRI : 8'bz;
    
    initial
    begin
        $monitor("%d Data = %b, Data2 = %b, PDR_EN = %b PORT_EN = %b PORT_RD = %b RST = %b TRI2TRI = %b IO = %b Port_Read_Data = %b",
                    $time, DATA, DATA2, PDR_EN, PORT_EN, PORT_RD, RST, TRI2TRI, IO, PORT_READ_DATA);
    end

	initial 
    begin
         CLK = 1'b0;
         forever #10 CLK = ~CLK; 
    end
    
	
    initial
    begin
		RST = 1'b1;
		#20 RST = 1'b0;
        #20 DATA = 1'b1; PDR_EN = 1'b1; RST = 1'b1; DATA2 = 8'b10101010; PORT_EN = 1'b1; PORT_RD = 1'b1;
        #20 PORT_RD = 1'b0;
        #100 $finish;
    end


endmodule
