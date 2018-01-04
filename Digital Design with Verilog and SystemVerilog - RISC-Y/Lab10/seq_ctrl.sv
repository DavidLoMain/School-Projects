`timescale 1ns /1ns

module seq_ctrl(input [6:0] ADDR, 
				input [3:0] OPCODE,
				input [1:0] PHASE,
				input [3:0] ALUFLAGS,
				input IFlag,
				input CLK,
				output reg IR_EN,
				output reg A_EN,
				output reg B_EN,
				output reg PDR_EN,
				output reg PORT_EN,
				output reg PORT_RD,
				output reg PC_EN,
				output reg PC_LOAD,
				output reg ALU_EN,
				output reg ALU_OE,
				output reg RAM_OE,
				output reg RDR_EN,
				output reg RAM_CS );

	always @(posedge CLK)
	begin
		casex({PHASE, ADDR, OPCODE})	
			// Cycle 0 Enables IR to read instruction
			13'b00_xxxxxxx_xxxx: {IR_EN, A_EN, B_EN, PDR_EN, PORT_EN, PORT_RD,
								    PC_EN, PC_LOAD, ALU_EN, ALU_OE, RAM_OE, RDR_EN, RAM_CS} = 13'b1000000000001;
		
		
		    // Cycle 1 I/O Port OpCode: Load
            13'b01_1000011_0000: {IR_EN, A_EN, B_EN, PDR_EN, PORT_EN, PORT_RD,
                                    PC_EN, PC_LOAD, ALU_EN, ALU_OE, RAM_OE, RDR_EN, RAM_CS} = 13'b0000100000110;
                                                         
            // Cycle 1 I/O Port OpCode: Store         
            13'b01_1000011_0001: {IR_EN, A_EN, B_EN, PDR_EN, PORT_EN, PORT_RD,
                                    PC_EN, PC_LOAD, ALU_EN, ALU_OE, RAM_OE, RDR_EN, RAM_CS} = 13'b0000010000000;
		
		    // Anything not changed in Cycle 2 keeps the previous state from Cycle 1
			//Cycle 2 ALU A Opcode: 2-7 (Add,Sub, And, Or, Xor, Not)
			13'b10_1000000_xxxx:
			begin
            if(OPCODE == 4'b0010 | OPCODE == 4'b0011 | OPCODE == 4'b0100 | OPCODE == 4'b0101 | OPCODE == 4'b0110 | OPCODE == 4'b0111)
                                  {IR_EN, A_EN, B_EN, PDR_EN, PORT_EN, PORT_RD,
                                   PC_EN, PC_LOAD, ALU_EN, ALU_OE, RAM_OE, RDR_EN, RAM_CS} = {IR_EN, 1'b1, B_EN, PDR_EN, PORT_EN, PORT_RD,
                                                                                              PC_EN, PC_LOAD, ALU_EN, ALU_OE, RAM_OE, RDR_EN, RAM_CS};
			end
			//Cycle 2 ALU B Opcode: 2-6(Add,Sub, And, Or, Xor)
			13'b10_1000001_xxxx:
			begin
			if(OPCODE == 4'b0010 | OPCODE == 4'b0011 | OPCODE == 4'b0100 | OPCODE == 4'b0101 | OPCODE == 4'b0110)
								  {IR_EN, A_EN, B_EN, PDR_EN, PORT_EN, PORT_RD,
					               PC_EN, PC_LOAD, ALU_EN, ALU_OE, RAM_OE, RDR_EN, RAM_CS} = {IR_EN, A_EN,1'b1, PDR_EN, PORT_EN, PORT_RD,
                                                                                              PC_EN, PC_LOAD, ALU_EN, ALU_OE, RAM_OE, RDR_EN, RAM_CS};
			end
			// Cycle 2 Port Direction Register OpCode: Load/Store
			13'b10_1000010_000x: {IR_EN, A_EN, B_EN, PDR_EN, PORT_EN, PORT_RD,
					                PC_EN, PC_LOAD, ALU_EN, ALU_OE, RAM_OE, RDR_EN, RAM_CS} = {IR_EN, A_EN, B_EN,1'b1,PORT_EN, PORT_RD,
                                                                                               PC_EN, PC_LOAD, ALU_EN, ALU_OE, RAM_OE, RDR_EN,1'b0};
		
		    // Cycle 3 Update PC and check for Branches, clear enables previously set
	        13'b11_xxxxxxx_xxxx: {IR_EN, A_EN, B_EN, PDR_EN, PORT_EN, PORT_RD,
                                    PC_EN, PC_LOAD, ALU_EN, ALU_OE, RAM_OE, RDR_EN, RAM_CS} = 13'b0000001100001;		 
		
		endcase 
	end

endmodule