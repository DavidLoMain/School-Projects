`timescale 1ns / 1ns

module tb_alu_subsystem();

	reg [7:0] DATA;
	reg A_EN, B_EN, CLK, ALU_EN, ALU_OE;
	reg [3:0] OPCODE;
	
	wire [7:0] A, B, ALU_DATA;
	wire OF, SF, ZF, CF;
	
	
    // A Reg (CLK, EN, IN, OUT) Default size was 8 bits so no need to change parameter
    Scalable_Register areg(CLK, A_EN, DATA, A);
    
    // B Reg (CLK, EN, IN, OUT) Default size was 8 bits so no need to change parameter
    Scalable_Register breg(CLK, B_EN, DATA, B);
    
    // ALU (CLK, EN, OE, OPCODE, A, B, OUT, OF, SF, ZF, CF) Default size was 8-bits so no need to change parameter
    ALU alu1(CLK, ALU_EN, ALU_OE, OPCODE, A, B, ALU_DATA, OF, SF, ZF, CF);
    
	initial
	begin
		$monitor("%d Data = %b A_EN = %b B_EN = %b ALU_EN = %b ALU_OE = %b ALU_DATA = %b OPCODE = %b ALUFLAGS = %b%b%b%b", 
					$time, DATA, A_EN, B_EN, ALU_EN, ALU_OE, ALU_DATA, OPCODE, OF, SF, ZF, CF);
	end
	
	initial 
    begin
         CLK = 1'b0;
         forever #10 CLK = ~CLK; 
    end
	
	
	initial
	begin
			DATA = 8'b00000000; A_EN = 1'b0; B_EN = 1'b0; ALU_EN = 1'b0; ALU_OE = 1'b0; OPCODE = 4'b0010;
		#50 DATA = 8'b00000011; A_EN = 1'b1; B_EN = 1'b1; ALU_EN = 1'b1; ALU_OE = 1'b1;
		#20 DATA = 8'b00000010;
		#20 A_EN = 1'b0; DATA = 8'b00000001;
		#20 ALU_EN = 1'b0; DATA = 8'b00001111;
		#20 ALU_OE = 1'b0;
		
		#100 $finish;
	end
endmodule